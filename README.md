# CarsApiApp - Android Client PoC

Este é um projeto Android desenvolvido como uma **Prova de Conceito (PoC)** para estudar e colocar em prática conceitos modernos de desenvolvimento mobile, arquitetura e integração com APIs.

O objetivo principal deste aplicativo é permitir o cadastro de carros, integrando-se com um backend robusto.

## 🚀 Tecnologias e Arquitetura

O projeto utiliza o que há de mais moderno no ecossistema Android:

- **Jetpack Compose**: UI declarativa e moderna.
- **Clean Architecture**: Separação clara de responsabilidades entre camadas (Data, Domain, UI).
- **MVVM (Model-View-ViewModel)**: Padrão de arquitetura de interface para facilitar testes e manutenção.
- **Hilt (Dagger)**: Injeção de dependências para um código mais modular e desacoplado.
- **Coroutines & Flow**: Gerenciamento de tarefas assíncronas e fluxo de dados reativo.
- **Retrofit**: Consumo de APIs REST.

## 🌐 Integração com Backend

Este aplicativo consome a API **ArquiteturaSpring**, que fornece os endpoints necessários para o gerenciamento dos carros.

> [!IMPORTANT]
> A API não está publicada em um ambiente de produção. Para que o aplicativo funcione, o backend deve estar sendo executado localmente.

- **Repositório da API**: [ArquiteturaSpring](https://github.com/BortoletoEric/ArquiteturaSpring)
- **Status da API**: Consumo Local.

## 🛠️ Funcionalidades

- **Seleção de Montadora**: Dropdown com opções predefinidas (HONDA, TOYOTA, NISSAN, MITSUBISHI) para garantir a integridade dos dados enviados à API.
- **Cadastro de Tipo**: Campo de texto para inserção manual do modelo/tipo de chave do veículo.
- **Feedback de Estado**: Tratamento completo de estados da UI (Idle, Loading, Success, Error).
- **Previews de Composição**: Suporte completo ao Compose Preview para agilizar o desenvolvimento da UI.

## 📖 Como Rodar

1. Certifique-se de que o backend [ArquiteturaSpring](https://github.com/BortoletoEric/ArquiteturaSpring) esteja rodando localmente.
2. Configure o endereço da API no aplicativo (geralmente apontando para `localhost` ou o IP da sua máquina).
3. Compile e execute o projeto `CarsApiApp` no Android Studio.

---

*Este projeto é focado em estudos e na aplicação de boas práticas de engenharia de software.*
