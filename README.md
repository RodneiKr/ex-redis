# Exemplo: springboot com cache (Redis)
## Antes de rodar o app executar:
- docker  run  --rm  --name ex-redis  -p 6379:6379  -d  redis

## Rodando o app a primeira vez teremos a seguinte saída:
### contatoService.get(1)
###### Contato[id=1, nome=fulano A, telefone=98765-4321]
### contatoService.get(2)
###### Contato[id=2, nome=fulano BB, telefone=97531-8642]
### contatoService.get(3)
###### Contato[id=3, nome=fulano CCC, telefone=24680-1357]
###### Contato[id=2, nome=fulano BB, telefone=97531-8642]

## Rodando o app a SEGUNDA vez teremos a seguinte saída:
###### Contato[id=1, nome=fulano A, telefone=98765-4321]
###### Contato[id=2, nome=fulano BB, telefone=97531-8642]
###### Contato[id=3, nome=fulano CCC, telefone=24680-1357]
###### Contato[id=2, nome=fulano BB, telefone=97531-8642]

## Conclusão sobre o método contatoService.get():
### na primeira vez:
- o @Cacheable verifica que não consta o Contato no cache;
- o metodo é executado (System.out.println("contatoService.get(id)"));
- o método retorna o Contato do Map;
- o @Cacheable armazena o Contato no cache.
### na segunda vez:
- o @Cacheable verifica que CONSTA o Contato no cache;
- o @Cacheable NÃO executa o método e retorna o Contato do cache..
