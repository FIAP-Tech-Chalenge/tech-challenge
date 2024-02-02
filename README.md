
# Tech Challenge Fase 1 - Grupo 15

## Grupo 15
- Matheus Nunes Almeida Werneck Telles
- Lucas Gelhen Rigon
- Ricardo Luis Machado

## Links Úteis
- Miro Board: [Miro - Fluxo de Eventos](https://miro.com)
- Swagger UI: [Localhost Swagger](http://localhost:8080/swagger-ui/)
- Swagger UI: [Railway Swagger](https://fiap-production.up.railway.app/swagger-ui/index.html#/)

## Sobre o Projeto
Este documento apresenta o fluxo do Tech Challenge realizado como parte da pós-graduação. O desafio envolveu a criação de um sistema de backend para uma lanchonete.

### Fluxo de Eventos da Lanchonete (Totem)

#### Contexto: Identificação
- **Identificação do Cliente**: O cliente escolhe se quer se identificar ou não. Isso pode influenciar a personalização do serviço e a rapidez do processo de pedido.

#### Contexto: Gerenciamento de Produtos
- **Criação de Produto**: Adicionar novos produtos ao sistema, definindo características como preço, categoria e descrição.
- **Edição de Produto**: Atualizar informações de produtos existentes, como alterar preços, descrições ou disponibilidade.
- **Exclusão de Produto**: Remover produtos do catálogo quando necessário.
- **Busca de Produto por Categoria**: Buscar produtos existentes por categoria.

#### Contexto: Criação do Pedido
- **Criação de Pedido**: O cliente ou o operador do totem cria um pedido, selecionando produtos e quantidades desejadas.
- **Adição de Itens ao Pedido**: Possibilidade de adicionar ou modificar itens no pedido antes da finalização.
- **Confirmação de Pedido**: O cliente confirma o pedido, possibilitando avançar para o checkout.

#### Contexto: Checkout
- **Finalização do Pedido**: O pedido é enviado para a fila e seu status é alterado.

#### Contexto: Listagem de Pedidos
- **Lista de Pedidos**: Lista todos os pedidos na fila.
