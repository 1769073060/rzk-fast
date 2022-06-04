FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD rzk-fast.jar  /rzk-fast.jar
RUN bash -c 'touch /rzk-fast.jar'
ENTRYPOINT ["java","-jar","/rzk-fast.jar"]
