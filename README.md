# 🛒 EcommerceApp - Guia de Estudo Android

Este é um projeto de aplicação de E-commerce desenvolvido em Android Nativo (Kotlin) com o objetivo de servir como material de estudo para integração de tecnologias modernas do ecossistema Android.

## 🚀 Tecnologias e Bibliotecas Utilizadas

A aplicação utiliza as melhores práticas sugeridas pela Google para o desenvolvimento de aplicações robustas e escaláveis:

- **[Kotlin](https://kotlinlang.org/):** Linguagem de programação moderna e concisa.
- **[MVVM (Model-View-ViewModel)](https://developer.android.com/topic/libraries/architecture/viewmodel):** Padrão de arquitetura para separação de responsabilidades.
- **[Dagger Hilt](https://dagger.dev/hilt/):** Injeção de Dependência para gestão eficiente de componentes.
- **[Room Database](https://developer.android.com/training/data-storage/room):** Persistência de dados local (Carrinho de Compras).
- **[Firebase Firestore](https://firebase.google.com/docs/firestore):** Base de dados NoSQL em tempo real para catálogo de produtos e categorias.
- **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** Para operações assíncronas e gestão de threads.
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata):** Para uma UI reativa baseada em observação de estados.
- **[View Binding & Data Binding](https://developer.android.com/topic/libraries/data-binding):** Para interação segura com os componentes de layout XML.
- **[Glide](https://github.com/bumptech/glide):** Carregamento e cache de imagens de forma otimizada.

## 🏗️ Arquitetura do Projeto

O projeto está dividido em pacotes lógicos para facilitar a manutenção:

- `di`: Configurações de Injeção de Dependência (Módulos Hilt).
- `model`: Classes de dados (Entities do Room e objetos de domínio).
- `room`: Configuração da base de dados local, DAOs e instâncias.
- `repo`: Repositório que atua como "única fonte de verdade", mediando dados entre Firestore e Room.
- `viewmodel`: Lógica de negócio e preparação de dados para a UI.
- `views`: Atividades e componentes visuais.
- `util`: Adaptadores de RecyclerView e classes auxiliares.

## 📱 Funcionalidades

1.  **Catálogo de Categorias:** Carregamento dinâmico de categorias via Firestore.
2.  **Lista de Produtos:** Filtro automático de produtos por categoria selecionada.
3.  **Detalhes do Produto:** Visualização ampliada com opção de adicionar ao carrinho local.
4.  **Carrinho de Compras:**
    - Persistência local com Room (os itens permanecem após fechar a app).
    - Remoção individual de itens.
    - Limpeza total do carrinho.
5.  **Checkout Simulado:** Envio dos itens comprados de volta para o Firestore (coleção `purchases`).

## ⚙️ Como Configurar o Projeto (Estudo)

Para rodar este projeto, precisará de configurar o seu próprio projeto no Firebase:

1.  Crie um projeto no [Consola do Firebase](https://console.firebase.google.com/).
2.  Adicione uma aplicação Android com o package name: `com.example.ecommerceapp`.
3.  Descarregue o ficheiro `google-services.json` e coloque-o na pasta `app/`.
4.  No Firestore, crie a seguinte estrutura:
    - Coleção `categories` -> Documentos com campo `name`.
    - Dentro de cada categoria, sub-coleção `products` -> Documentos com `title`, `price` (number) e `imageUrl`.
5.  Sincronize o Gradle e execute a aplicação.

## 📝 Conceitos Chave para Estudar neste Código

- **Injeção de Contexto:** Observe como o `@ApplicationContext` é usado no `FirebaseModule` para fornecer o Room.
- **Reatividade:** Veja como o `MyViewModel` expõe um `LiveData` que a `CartActivity` observa para atualizar a lista automaticamente.
- **Separação de Dados:** O `Repository` lida com a complexidade de decidir se os dados vêm da Cloud (Firestore) ou do Disco (Room).


<img width="424" height="932" alt="Captura de ecrã 2026-04-02 112502" src="https://github.com/user-attachments/assets/487df7d3-bdaf-4263-baff-d98f60f1dbcb" />
<img width="421" height="934" alt="Captura de ecrã 2026-04-02 112513" src="https://github.com/user-attachments/assets/db9c85cd-c56c-4f9b-8bc4-f785fc1ff8a0" />
<img width="425" height="934" alt="Captura de ecrã 2026-04-02 112524" src="https://github.com/user-attachments/assets/89a0bda0-3dad-4cac-a7d5-02d21e8f8e6d" />
<img width="407" height="934" alt="Captura de ecrã 2026-04-02 112557" src="https://github.com/user-attachments/assets/d6ffb999-a51c-4cc3-8e13-b4bcbb827c26" />
