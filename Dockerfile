FROM amazoncorretto:17

WORKDIR /app

COPY . /app

CMD ["./gradlew", "bootRun"]

# Lombok 설치
RUN java -jar lombok.jar install /alog-service-release