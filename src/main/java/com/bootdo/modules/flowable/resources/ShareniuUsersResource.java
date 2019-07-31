package com.bootdo.modules.flowable.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteGroup;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.flowable.ui.common.model.UserRepresentation;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/idm")
public class ShareniuUsersResource extends  BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShareniuUsersResource.class);
    @Autowired
    protected ObjectMapper objectMapper;
    @RequestMapping(value = "/rest/editor-users", method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(@RequestParam(value = "filter", required = false) String filter) {

        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken keycloakAuthenticationToken= (KeycloakAuthenticationToken) authentication;
        String tokenString = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getTokenString();
        System.out.println(tokenString);
        List<? extends User> matchingUsers = findUsersByNameFilter(filter,tokenString);
        List<UserRepresentation> userRepresentations = new ArrayList<>(matchingUsers.size());
        for (User user : matchingUsers) {
            userRepresentations.add(new UserRepresentation(user));
        }
        return new ResultListDataRepresentation(userRepresentations);
    }


    public List<RemoteUser> findUsersByNameFilter(String filter,String tokenString) {
        JsonNode json = callRemoteIdmService( "http://t1.it663.com:38001/api/users/search?username=" + encode(filter),tokenString);
        if (json != null) {
            return parseUsersInfo(json);
        }
        return new ArrayList<>();
    }

    protected JsonNode callRemoteIdmService(String url,String tokenString) {
        HttpGet httpGet = new HttpGet(url);
       httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + tokenString);

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
            clientBuilder.setSSLSocketFactory(sslsf);
        } catch (Exception e) {
            LOGGER.warn("Could not configure SSL for http client", e);
        }

        CloseableHttpClient client = clientBuilder.build();

        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return objectMapper.readTree(response.getEntity().getContent());
            }
        } catch (Exception e) {
            LOGGER.warn("Exception while getting token", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.warn("Exception while closing http client", e);
                }
            }
        }
        return null;
    }
    protected String encode(String s) {
        if (s == null) {
            return "";
        }

        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            LOGGER.warn("Could not encode url param", e);
            return null;
        }
    }

    protected List<RemoteUser> parseUsersInfo(JsonNode json) {
        List<RemoteUser> result = new ArrayList<>();
        if (json != null && json.isArray()) {
            ArrayNode array = (ArrayNode) json;
            for (JsonNode userJson : array) {
                result.add(parseUserInfo(userJson));
            }
        }
        return result;
    }

    /**
     * {"userId":"1","username":"admin","name":"超级管理员","email":"admin@gmailcom","mobile":"17699999999","status":"1"}
     * @param json
     * @return
     */
    protected RemoteUser parseUserInfo(JsonNode json) {
        RemoteUser user = new RemoteUser();
        user.setId(json.get("username").asText());
        user.setFirstName(json.get("name").asText());
        user.setLastName(json.get("name").asText());
        user.setEmail(json.get("email").asText());
        user.setFullName(json.get("name").asText());
        if (json.has("tenantId") && !json.get("tenantId").isNull()) {
            user.setTenantId(json.get("tenantId").asText());
        }

        if (json.has("groups")) {
            for (JsonNode groupNode : ((ArrayNode) json.get("groups"))) {
                user.getGroups().add(new RemoteGroup(groupNode.get("id").asText(), groupNode.get("name").asText()));
            }
        }

        if (json.has("privileges")) {
            for (JsonNode privilegeNode : ((ArrayNode) json.get("privileges"))) {
                user.getPrivileges().add(privilegeNode.asText());
            }
        }

        return user;
    }


}
