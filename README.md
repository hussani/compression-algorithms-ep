# Algoritmos de Compressão

> Exercício-Programa 3 da disciplina MAC5710
>
> Hussani Silva de Oliveira - NUSP 12578741


## Instruções para compilar e executar

#### Requisitos
- [Maven](http://maven.apache.org/)
- JDK 11+

#### Executando como programa Java

Compile o projeto
```shell
mvn clean compile
```

A execução espera uma entrada SDTIN. Você pode utilizar pipes ou redirecionar o conteúdo de arquivos.

Exemplo
```shell
java -classpath target/classes com.hussani.compression.App (5bits|huffman) (compression|decompression) < {stdin file} 
```

Exemplo
```shell
java -classpath target/classes com.hussani.compression.App huffman compression < ~/Downloads/test-data.txt
```
Ou
```shell
cat ~/Downloads/test-data.txt | java -classpath target/classes com.hussani.compression.App 5bits decompression
```

Nota:

Uma flag `-n` pode ser utilizada como terceiro argumento para transformar a entrada em letras minúsculas.

#### Executando como pacote JAR

Gere o pacote
```shell
mvn clean package
```

Exececute o pacote
```shell
stdin | java -jar target/ep-3-huffman-1.0-SNAPSHOT.jar (5bits|huffman) (compression|decompression)
```
