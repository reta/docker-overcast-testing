Using Overcast and Docker for writing component tests 
==============

- Build Overcast

		git clone https://github.com/xebialabs/overcast
		
		cd overcast
		
		./gradlew install

- Build project with tests

         mvn package test
         
- Run using Spring Boot Maven plugin (just for fun)

         mvn spring-boot:run

         