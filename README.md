# kafka-example

1. Apache Kafka için pom.xml'e eklemeniz gereken dependency:
   
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

--------------------------------------------------------------------------------------------------------------------
2. Apache Kafka için projenin application.yml'a eklemeniz gerekenler:

		spring:
		  application:
		    name: kafka-example
		  kafka:
		    producer:
		      bootstrap-servers: localhost:9092
		      key-serializer: org.apache.kafka.common.serialization.StringSerializer
		      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
		    consumer:
		      bootstrap-servers: localhost:9092
		      group-id: my-group
		      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
		      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
	
--------------------------------------------------------------------------------------------------------------------
3. DOCKER:

	3.1. İlk olarak, Docker Compose'un sisteminizde yüklü olup olmadığını kontrol edin:

		docker-compose --version

	Eğer yüklü değilse, Docker Desktop'ı indirip kurabilirsiniz. Docker Desktop, Docker Compose ile birlikte gelir.



	3.2. Bir metin düzenleyici kullanarak proje klasörünüzde "docker-compose.yaml" adında bir dosya oluşturun:

		version: '3.8'
		services:
		  kafka:
		    image: wurstmeister/kafka
		    environment:
		      - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092  # application.yaml'da bootstrap-servers'a verdiğimiz portla aynı olmalı
		      - KAFKA_LISTENER_SECURITY_PROTOCOL=PLAINTEXT
		      - KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092
		      - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
		    ports:
		      - "9092:9092"
		    networks:
		      - kafka-net
		  zookeeper:
		    image: wurstmeister/zookeeper
		    ports:
		      - "2181:2181"
		    networks:
		      - kafka-net
		networks:
		  kafka-net:
		    driver: bridge


	3.3. docker-compose.yaml dosyasının bulunduğu dizinde şu komutu çalıştırın:
              
		docker-compose up


	3.4. Hizmetleri durdurmak için şu komutu kullanabilirsiniz:
 
		docker-compose down


--------------------------------------------------------------------------------------------------------------------
4. Topicteki dataları görüntülemek için aşağıdaki komutu bilgisayarınızın terminaline girebilirsiniz. <TOPIC_NAME> yerine kendi topic adınızı yazmalısınız:

		kafka-console-consumer --bootstrap-server localhost:9092 --topic <TOPIC_NAME> --from-beginning

--------------------------------------------------------------------------------------------------------------------

5. Postman aracılığıyla Controller'a bir istek atarak topice data bırakabilirsiniz. Payload'u istediğiniz şekilde düzenleyebilirsiniz.
