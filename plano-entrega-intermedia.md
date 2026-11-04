# Plano de implementação: Entrega Intermédia (ERP / PO)

As referências `§` são às secções do enunciado do projeto.

## Âmbito da entrega (6 valores, 2,5 de testes automáticos)

- Leitura do ficheiro `import` e representação em memória (§5)
- Menu principal: Abrir, Guardar, Mostrar data, Avançar data (§4.2)
- Menu Empresas: registar, mostrar, mostrar todas, alterar inventário, mostrar produtos da empresa (§4.3)
- Menu Produtos: registar, mostrar, mostrar todos, pesquisa (§4.4)
- Menu Clientes: registar, mostrar, mostrar todos (§4.5)
- Menu Compras: "Efectuar compra", que só abre o menu do cabaz (§4.6.1)
- Todos os outros comandos têm de existir, com `execute()` vazio

Não entregar a intermédia dá 0 nessa componente.

## Regras gerais

- Não alterar `App`, `Prompt`, `Message`, `Label`, `erp.app.exception` nem os menus e comandos de abertura de menus fornecidos (§3.1).
- Nenhum código de interação em `erp.core`; a lógica de negócio fica toda no `core` (§3).
- Exceções da `app` só se lançam nos comandos. O domínio lança as suas, em `erp.core.exception`, e o comando converte (§3, §3.1).
- Em caso de erro, o comando não altera o estado e lança a exceção correspondente (§3, §3.1).
- Em geral, pedem-se todos os dados antes de validar (§4, 1.º parágrafo).

## Ordem de trabalho

### 0. Preparação
- [X] Esqueleto a compilar e a correr
- [X] Script de testes: `java -Dimport=... -Din=... -Dout=... erp.app.App` e `diff -b teste.out teste.outhyp` (§6)
- [X] Repositório git, com um commit por passo

### 1. Entidades do domínio (§1)
- [X] `ProductCategory` (enum): `ALIMENTAR`, `ELECTRONICA`, `VESTUARIO` (§1.1)
- [X] `Product` (abstrata): id automático a partir de 1, nome não vazio, preço base inteiro positivo, categoria, prazo base (§1.1)
- [X] `NationalProduct` (§1.1.2): mais de 10000 unidades totais dá 90% do preço base, menos de 100 dá 130%, senão o preço base
- [X] `ImportedProduct` (§1.1.3): país de origem não vazio, taxa real; recomendado = `base * (100 + t) / 100`. O recomendado nunca se arredonda.
- [X] `Company` (§1.2): id automático, nome não vazio
- [X] `SuppliedProduct`: unidades, fator positivo, preço de venda (§1.2, §1.5)
- [x] `Company.changeInventory` (§1.2.1)
  - inventário nunca negativo e fator positivo
  - a 1.ª disponibilização exige quantidade e fator positivos
  - com 0 unidades o produto deixa de estar disponível, mas continua a existir
  - se for inválido, não tem efeito
- [x] `Client` (§1.3): email único e não vazio, nome não vazio, gasto total
- [x] `Basket` mínimo (§1.4): um por cliente e empresa, guardado no `Client` (persistente)

### 2. `Erp` e `ErpManager` (§3.1)
- [ ] `ErpManager` mantém o `Erp` atual e é o ponto de entrada da `app`
- [ ] Geradores de ids no `Erp`, serializados, para continuarem depois de Abrir (§1.1, §1.2, §1.9)
- [ ] Opções dos formulários (tipo, categoria, critério) obtidas do domínio via `ErpManager` (§4, antes de §4.1)
- [ ] Javadoc na classe `Erp` (critério de avaliação manual)

### 3. Leitura do `import` (§5)
- [ ] Linhas: `RATE`, `COMPANY`, `CLIENT:nome:email`, `NATIONAL`, `IMPORTED`, `SUPPLY`; usar `String.split(":")`
- [ ] UTF-8; entradas bem formadas; há sempre pelo menos uma `RATE`
- [ ] Preço base e tarifa inteiros; taxa e fator reais
- [ ] Ids por ordem de leitura; `SUPPLY` refere entidades de linhas anteriores
- [ ] O ficheiro só se usa no arranque (§4.2.1, §6)

### 4. Menu principal (§4.2)
- [ ] Mostrar data: `Message.currentDate()` (§4.2.2); a data começa em 1 (§1.7)
- [ ] Avançar data: `Prompt.daysToAdvance()`; só valores positivos, senão não tem efeito; avança dia a dia (§4.2.3, §1.7)
- [ ] Abrir (§4.2.1): `Prompt.openFile()`, `FileOpenFailedException` se falhar; substitui todo o estado; se houver alterações por guardar, pergunta `Prompt.saveBeforeExit()`
- [ ] Guardar (§4.2.1): usa o ficheiro associado, senão `Prompt.newSaveAs()`; não faz nada se não houver alterações
- [ ] Sair nunca guarda (§4.2.1)

### 5. Menu Empresas (§4.3)
- [ ] Registar: `Prompt.companyName()`, `Message.registrationSuccessful()`, `CompanyRegistrationFailedException` (§4.3.1)
- [ ] Mostrar e Mostrar todas: formato `id - nome`, ordenadas por id (§4.3.2, §4.3.3)
- [ ] Alterar inventário (§4.3.4): pede empresa, produto, `Prompt.amountToUpdate()` e `Prompt.priceFactor()`
  - sucesso: `Message.supplySuccessful()`
  - inválido: `Message.cannotChangeInventory()`, sem efeito
- [ ] Mostrar produtos da empresa: ordenados por id de produto, formato `idEmpresa - idProduto - unidades - factor - preço de venda`; só os disponíveis (§4.3.5, §4.1, §1.2.1)
- [ ] Produto não disponibilizado pela empresa: `ProductNotSuppliedException` (§4, depois da tabela)

### 6. Menu Produtos (§4.4)
- [ ] Registar: pede tipo, nome, preço base, categoria e prazo, por esta ordem; se `IMPORTADO`, pede também país e taxa; falha com `ProductRegistrationFailedException` (§4.4.1)
- [ ] Mostrar e Mostrar todos: formato de §4.1, ordenados por id; o preço base vem antes do recomendado (§4.4.2, §4.4.3)
- [ ] Pesquisa (§4.4.4)
  - critérios `NOME`, `CATEGORIA`, `ORIGEM`
  - comparação sem distinguir maiúsculas
  - resultado por id; se não houver resultados, não imprime nada
  - `ORIGEM` só encontra produtos importados

### 7. Menu Clientes (§4.5)
- [ ] Registar: pede o nome e depois o email; email repetido falha com `ClientRegistrationFailedException` (§4.5.1)
- [ ] Mostrar: `email - nome - gasto total` (§4.5.2)
- [ ] Mostrar todos: por nome e depois por email, sem distinguir maiúsculas (§4.5.3)

### 8. Efectuar compra (§4.6.1)
- [ ] Pede cliente e empresa, valida, e abre o menu do cabaz desse cliente nessa empresa (criado vazio se não existir)

### 9. Fecho
- [ ] Todos os comandos restantes existem
- [ ] Testes a passar; formatos, ordenações e locale conferidos
- [ ] Sem lixo: código comentado, imports não usados, ficheiros soltos (desconto até 1,00)

## Armadilhas de implementação

**Formatos** (§1.5, §4.1)
- `String.format(Locale.US, ...)` em tudo o que tenha decimais. Sem isto, `pt_PT` escreve `15,00` e o `diff` falha.
- Dinheiro com 2 casas, ponto decimal: `15.00`.
- Fator e taxa alfandegária aparecem como o `double` em texto (`0.9`, `1.15`, `30.0`): usar `%s`, nunca `%.1f`.
- Produto: `id - tipo - nome - categoria - preço base - preço recomendado - prazo base - unidades totais [- país - taxa]`. O tipo é `NACIONAL` ou `IMPORTADO`.

**Arredondamento** (§1.5)
- Único arredondamento do sistema, no preço de venda:
  `Math.round(recomendado * fator * 100) / 100.0`
- No produto nacional, calcular com percentagens inteiras (`base * 90 / 100.0`) para evitar erros de vírgula flutuante.

**Serialização** (§1.9, §4.2.1)
- Todas as classes alcançáveis a partir do `Erp` implementam `java.io.Serializable`, com `@java.io.Serial private static final long serialVersionUID`.
- Os enums já são serializáveis.
- Cuidado com campos que não sejam serializáveis (lambdas, objetos da `app`).

**Estruturas de dados**

| Onde | Estrutura | Porquê |
|---|---|---|
| Empresas, produtos, compras no `Erp` | `TreeMap<Integer, ...>` | listagens por id |
| Clientes no `Erp` | `Map<String, Client>` pelo email, ordenar ao listar | listagem por nome, depois email |
| Produtos de uma `Company` | `TreeMap<Integer, SuppliedProduct>` (id do produto) | "mostrar produtos" por id |
| Empresas num `Product` | `HashMap<Integer, SuppliedProduct>` | só procura e soma de unidades |
| Cabazes num `Client` | `HashMap<Integer, Basket>` (id da empresa) | um por empresa |

- Não devolver os mapas internos; devolver cópias ou vistas só de leitura.

**Exceções: domínio → `app`** (a converter no comando)

| Domínio (`erp.core.exception`) | `app` (`erp.app.exception`) |
|---|---|
| `UnknownCompanyException` | `UnknownCompanyKeyException` |
| `UnknownProductException` | `UnknownProductKeyException` |
| `UnknownClientException` | `UnknownClientKeyException` |
| `InvalidCompanyException` | `CompanyRegistrationFailedException` |
| `InvalidProductException` | `ProductRegistrationFailedException` |
| `InvalidClientException` | `ClientRegistrationFailedException` |
| `ProductNotSuppliedException` | `ProductNotSuppliedException` (nome igual: usar o nome completo) |
| `UnavailableFileException` | `FileOpenFailedException` |
| `MissingFileAssociationException` | pede `Prompt.newSaveAs()` e chama `saveAs` |

Já existentes no esqueleto: `ImportFileException`, `MissingFileAssociationException`, `UnavailableFileException`, `UnrecognizedEntryException`. Confirmar o construtor de cada exceção da `app` antes de a lançar.

## Avaliação manual (checklist)

- [ ] Atributos não públicos (0,40) e nada `static` sem autorização (0,30)
- [ ] Serialização (0,20)
- [ ] Herança bem usada (1,00): `Product`, `NationalProduct`, `ImportedProduct`
- [ ] Estruturas de dados corretas (0,50)
- [ ] Separação `app`/`core` (0,60)
- [ ] Javadoc na classe `Erp` (0,20)
- [ ] Sem violações de regras de codificação Java (desconto de 0,15)
- [ ] Sem lixo no código (desconto até 1,00)

## Dúvidas para o corpo docente (po-tagus@disciplinas.tecnico.ulisboa.pt)

1. Na secção 5, a linha `IMPORTED` tem um campo final `prazoInternacional` que não aparece no exemplo nem é descrito noutro sítio.
2. Em §4.3.6, "clientes que já interagiram com a empresa": só os que têm compras, ou também os que só têm cabaz?
3. O email do cliente compara-se sem distinguir maiúsculas na procura e no registo? §4.5.3 só diz que a ordenação ignora maiúsculas.
4. Em §4.8 o pacote da exceção é `erp.app.exceptions`, mas as restantes estão em `erp.app.exception`.
