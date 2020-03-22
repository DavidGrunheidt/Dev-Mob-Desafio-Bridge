### Perguntas

***Qual seu nome completo?***

David Grunheidt Vilela Ordine   

***Você realizou a prova em Android ou Flutter?***

Flutter

***Você testou em qual sistema operacional/dispositivo/emulador?***

Android

***Mais algum comentário ou observação que gostaria de fazer?***

As tarefas de T1 a T3 são extremamente simples. A partir de T4 comecei a tentar entender um pouco mais a aplicação pra resolver.

Para ordenar a lista de favoritos, primeiramente tentei fazer no metodo get favorites em favorites.dart. Porém reparei, durante o debug, que esse método é chamado inumeras vezes, desnecessariamente, em favorites_tab.dart. Então otimizei o método build da classe _FavoritesTabState pra armazenar a chamada de favorites.favorites em uma variavel. Assim, para renderizar a tela de favoritos, em vez de chamar favorites.favorites n vezes (n = numero de favoritos), ele só chama uma.

Também achei estranho, pras operações de adição de favoritos e remoção de favoritos, o endpoint retornar uma lista de favoritos e você renderizar a tela com aquela lista (inclusive isso tras alguns bugs, testando percebi que se você remove muito rapido, alguns ficam na atualziação de tela, e só saem se você esperar um pouco e adicionar outro ou remover novamente). Para correções futuras (caso este fosse um projeto no qual eu estivesse trabalhando), validaria a possibilidade de otimizar esta logica, fazendo requisições para o endpoint de maneira menos gastante, e trabalhando com as listas locais também na hora de adicionar ou remover, sem depender de resposta do endpoint.
