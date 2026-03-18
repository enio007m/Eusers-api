# multi-stage build separa compilação (maven) da execução (java) - reduz o tamanho da imagem final

# build stage - compila o projeto
# imagem com maven + java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
# copia todos os arquivos do projeto para dentro do container
COPY . .
# gera o .jar da aplicação (ignora testes)
RUN mvn clean package -DskipTests

# run stage - executa a aplicação
# imagem mais leve apenas com java 21
FROM eclipse-temurin:21-jdk

WORKDIR /app
# copia o .jar gerado no stage de build para este container
COPY --from=build /app/target/*.jar app.jar

# expõe a porta da aplicação
EXPOSE 8001

# comando que executa o .jar da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
