Se você tiver com problema com javafx:

PASSO 1:
Primeiro vá em resources no nosso projeto do github na branch v1.1 ou alguma superior a essa e baixe esses outros arquivos na web;
- [JDK 8u202 (Oracle)](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [JavaFX SDK 24.0.1](https://gluonhq.com/products/javafx/)
- [SceneBuilder 24.0.0-RC1](https://gluonhq.com/products/scene-builder/)
- [MySQL Connector/J 9.3.0](https://dev.mysql.com/downloads/connector/j/)

PASSO 2:
Abra na barra Tools > Options > Java > JavaFX > Define o Scene Builder com a pasta onde vc baixou o scene builder, reconheceu? OK vamo pro proximo!

PASSO 3:
Agora ainda em Options > Java vá para Ant >  Add jar/zip (adiciona o arquivo javafx-sdk-24) || Add directory se vc quiser adicionar o diretorio inteiro(se tiver extraído) do javafx-sdk-24(se o outro der errado) > Em baixo em propriedades se não tiver digite: build.compiler.emacs=true > Aplica isso tudo e dps da Ok.

PASSO 4:
Agora clique novamente em Tools > Java Platforms > Add platform > Java Standard Editon > selecione a pasta onde baixou o java 8 > next > Escreva o nome da sua platform e da finish.

PASSO 5:
Ainda em Java Platform clica na plataforma q acabou de criar > Sources > adiciona os arquivos q baixou do javafx-sdk-24.

PASSO 6:
Seja feliz, a vida é mt curta pra se estressar com um aplicativo complexo, vamo pegar esses 40 pontos.
