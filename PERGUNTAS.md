### Perguntas

***Qual seu nome completo?***

David Grunheidt Vilela Ordine   

***Você realizou a prova em Android ou Flutter?***

Realizei em Flutter

***Você testou em qual sistema operacional/dispositivo/emulador?***

Testei no android, no meu celular, Redmi Note 7

***Mais algum comentário ou observação que gostaria de fazer?***

As tarefas de T1 a T3 são extremamente simples. 

Sobre T4:

Para ordenar a lista de favoritos, primeiramente tentei fazer no metodo get favorites em favorites.dart. Porém reparei, durante o debug, que esse método é chamado inumeras vezes, desnecessariamente, em favorites_tab.dart. Então otimizei o método build da classe _FavoritesTabState pra armazenar a chamada de favorites.favorites em uma variavel. Assim, para renderizar a tela de favoritos, em vez de chamar favorites.favorites n vezes (n = numero de favoritos), ele só chama uma.

Também achei estranho, pras operações de adição de favoritos e remoção de favoritos, o endpoint retornar uma lista de favoritos e você renderizar a tela com aquela lista (inclusive isso tras alguns bugs, testando percebi que se você remove muito rapido, alguns ficam, na hora de atualizar a tela, e só saem se você esperar um pouco e adicionar outro ou remover novamente). Para correções futuras (caso este fosse um projeto no qual eu estivesse trabalhando), validaria a possibilidade de otimizar esta logica, fazendo requisições para o endpoint de maneira menos gastante, e trabalhando com as listas locais também na hora de adicionar ou remover, sem depender de resposta do endpoint.

A razão que escolhi fazer em load é que, load é chamado tanto na inicialização da classe, quanto em add e remove, então, este foi o melhor lugar pra mim.

Sobre T5:

A tarefa também foi tranquila. Só fiquei com umas duvidas sobre fonte e tamanho, porque nunca mexi com essa parte de definição de estilos pegando as informações de uma ferramenta de design. Mas no final acredito que consegui renderizar o que era esperado.

Sobre T6:

Achei um bug, onde, ao clicar em "ver album" nos top 50, o atributo "artistName" da classe AlbumScreen vinha como o id do artista, pois a classe TrackMenu (na linha 18), estava passando para o construtor da classe AlbumScreen track.artists.first.id em vez de track.artists.first.name. Quando vamos para um album através da "Discografia" de um artista a informação artistName é passada correta.

Também não consegui descobrir onde tirar a informação "ano" mostrada nos estilos, (Ex: lá tem, em baixo do nome do album, {Nome artista} {ano}, {nº de musicas}). Analisei os jsons dos endpoints /artists e /albums e nenhum deles tinha essa informação.

Enviei um email para o Caio e ele disse que podia tratar estes bugs da forma que preferisse, então, os albums, substitui track.artist.first.id por ...first.name (só o nome do primeiro artista, pois quando tem mais de um as vezes parte do restante do texto fica escondido em baixo de "tocar"). Quanto ao ano, não mostrei a informação.

Também vi nos estilos do album que tem um icone "explict", mas nos atributos de ambos artistas e albums não existe nada que possa indicar se devo ou naõ mostrar esse icone, então ignorei ele também.


