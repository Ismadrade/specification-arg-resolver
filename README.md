# specification-arg-resolver

Specification Args Resolver é uma biblioteca alternativa, que facilita na criação de filtros e paginações.
Além de deixar o código mais bonito e legível, permite utilizar o criteria builder em conjunto.


## Configurando a biblioteca no projeto

### pom.xml

No seu arquivo xml, você precisará adicionar a seguinte dependencia

```xml
        <dependency>
            <groupId>net.kaczmarzyk</groupId>
            <artifactId>specification-arg-resolver</artifactId>
            <version>2.6.2</version>
        </dependency>
```

### Gradle

``` groovy
implementation group: 'net.kaczmarzyk', name: 'specification-arg-resolver', version: '2.6.2'
```

### Classe de Configuração

Após adicionar a biblioteca, será preciso criar a nossa classe de configuração:

```java
@Configuration
public class ResolverConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());

        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

}
```

### Implementando no Repository

Após adicionar a classe de configuração, precisamos fazer um extends do JpaSpecificationExecutor do pacote org.springframework.data.jpa.repository, para podermos utilizar as specifications em nossas consultas.
```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

}
```
 

## Uso Basico

Aqui temos um exemplo basico de como fazer uma implementação simples com a biblioteca.

Observe a requisição abaixo

```
GET http://localhost:8080/students?name=Mar
```

Com a implementação abaixo, é possível fazer uma busca pelo nome, com uma linha de codigo e sem criteria builder.

```java
@GetMapping("/all")
    public ResponseEntity<Page<Student>> getAllStudentsLikeByName(
            @Spec(path = "name", params = "name", spec = Like.class) Specification<Student> spec,
            @PageableDefault(page = 0, size = 10, sort = "studentId", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return 
                ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, pageable));

    }
```

O que seria equivalente a query abaixo

```jpaql
select s from Student s where s.name like '%Mar%'
```


## Tipos de Uso

Você pode utilizar diretamente na requisição, conforme mostrado no exemplo anterior, ou criando uma interface separada, conforme o exemplo abaixo.

```java
    @And({
            @Spec(path = "name", spec= Like.class),         
    })
    public interface LikeByName extends Specification<Student> {}
```

## Tipos de Specs Mais Conhecidas

* Like: Utilizado quando você quer buscar resultados semelhantes ao que foi digitado;
* LikeIgnoreCase: A mesma coisa que o Like, porém desconsiderando o case sensitive;
* Equals: Utilizado quando você quer buscar resultados igual ao que foi digitado;
* EqualsIgnoreCase: A mesma coisa que o Equals, porém desconsiderando o case sensitive;
* In: Quando você quer buscar resultados que contenha ao menos um dos valores digitados.

## AND e OR

Você pode utilizar o `@And`, caso queira que o retorno considere todos os filtros utilizados, e o operador `@Or`, 
caso queira considerar ao menos um dos parâmetros informados.

## Join

O Join permite ampliar o tipo de busca, utilizando outras classes que estão relacionadas com a sua entidade.
De forma resumida, com essa operação, você dará um join com outra entidade.


## Exemplos de Implementação

No código, foram criados alguns exemplos utilizando os operadores citados, além de um exemplo com o Join.
Vale lembrar que existem varias outras implementações, que você pode estar encontrando na documentação.

## Referencias:

[Documentação Oficial](https://github.com/tkaczmarzyk/specification-arg-resolver)
