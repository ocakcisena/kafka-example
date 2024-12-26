# kafka-example
apache-kafka

1. Kafka için pom.xml'e eklenen dependency:
   
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

3. Kafka için projenin application.yml'a eklenmesi gerekenler:

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
	

3. DOCKER:

docker-compose.yaml Dosyası Nasıl Çalıştırılır?

	a. Docker Compose’un Yüklü Olduğundan Emin Olun İlk olarak, Docker Compose'un sisteminizde yüklü olup olmadığını kontrol edin:

              docker-compose --version

Eğer yüklü değilse, Docker Desktop'ı indirip kurabilirsiniz. Docker Desktop, Docker Compose ile birlikte gelir.
	
	b. docker-compose.yaml Dosyasını Oluşturun Bir metin düzenleyici kullanarak proje klasörünüzde docker-compose.yaml adında bir dosya oluşturun.
	c. docker-compose Komutunu Kullanarak Çalıştırın docker-compose.yaml dosyasının bulunduğu dizinde şu komutu çalıştırın:
              docker-compose up
              
		○ Bu komut, tanımlı hizmetleri çalıştırır.
		○ Eğer terminalde loglar görmek istemiyorsanız -d (detached mode) bayrağını kullanabilirsiniz:
              docker-compose up -d
              
	d. Hizmetleri Durdurun Hizmetleri durdurmak için şu komutu kullanabilirsiniz:
              docker-compose down



  
4. Topicteki dataları görüntülemek için:
      kafka-console-consumer --bootstrap-server localhost:9092 --topic <TOPIC_NAME> --from-beginning


5. kafka-setup file:
   
örnek docker-compose.yaml:
      version: '3.8'
      services:
	kafka:
	  image: wurstmeister/kafka
	  environment:
	    - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092  # Advertise on localhost:9092 (adjust if necessary)
	    - KAFKA_LISTENER_SECURITY_PROTOCOL=PLAINTEXT
	    - KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092
	    - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
	  ports:
	    - "9092:9092"  # Expose port 9092 for external connections
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
