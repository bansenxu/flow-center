FROM registry.cn-hangzhou.aliyuncs.com/md5/alpine-flowable:latest
COPY maven/ /opt/
EXPOSE 8001
ENTRYPOINT ["java","-Dfile.encoding=UTF-8", "-Dspring.profiles.active=prod", "-Djava.security.egd=file:///dev/urandom","-jar","/opt/application.jar"]