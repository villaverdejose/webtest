# Backend — què fa cada fitxer

Projecte Maven, s'empaqueta com a `.war`, es desplega a Tomcat. Connecta amb PostgreSQL
i exposa les dades de la taula `cliente` de dues maneres: com a HTML (JSP) i com a JSON (per al frontend a Apache).

## Configuració del projecte

| Fitxer | Què és |
|---|---|
| `pom.xml` | Configuració de Maven: empaquetat `war`, versió de compilació de Java, i dependències (driver JDBC de PostgreSQL, API de Servlets, Gson per a JSON). |
| `src/main/webapp/WEB-INF/web.xml` | Descriptor de desplegament de l'aplicació web. Defineix la pàgina de benvinguda (`index.jsp`). Els Servlets **no** es declaren aquí, es registren amb anotacions (vegeu més avall). |

## Capa de dades (no són Servlets)

| Fitxer | Què és |
|---|---|
| `Cliente.java` | POJO senzill (id, nom). Representa una fila de la taula `cliente`. No té lògica, només dades. |
| `ClienteDAO.java` | Accés a la base de dades (DAO). Obre la connexió JDBC a PostgreSQL, executa `SELECT * FROM cliente` i retorna una `List<Cliente>`. És l'única classe que sap SQL; ni els Servlets ni els JSP accedeixen a la base de dades directament. |

## Servlets (reben peticions HTTP)

| Fitxer | Ruta | Què és |
|---|---|---|
| `ClienteServlet.java` | `/clientes` | Servlet clàssic. Crida `ClienteDAO`, posa la llista com a atribut de la petició, i la reenvia (`forward`) a `clientes.jsp` perquè la pinti com a HTML. Pensat per a la navegació normal dins de Tomcat. |
| `ClienteApiServlet.java` | `/api/clientes` | Servlet d'API. Crida el mateix `ClienteDAO`, però en lloc d'HTML retorna **JSON** (amb Gson) i afegeix la capçalera CORS (`Access-Control-Allow-Origin`). És el que consumeix l'`index.html` que viu a Apache, ja que aquest frontend és en un origen/port diferent. |

Els dos Servlets es registren mitjançant l'anotació `@WebServlet("...")` a la seva pròpia classe — per això `web.xml` no els necessita declarar.

## Vistes JSP (només HTML, sense lògica de dades)

| Fitxer | Què és |
|---|---|
| `index.jsp` | Pàgina de benvinguda del backend a Tomcat, amb un enllaç a `/clientes`. |
| `clientes.jsp` | Rep la llista de clients que li passa `ClienteServlet` i la pinta com a taula HTML. |
| `error.jsp` | Pàgina d'error mostrada si `ClienteServlet` captura una `SQLException` en consultar la base de dades. |

## Flux resumit

```
Navegador → /clientes      → ClienteServlet    → ClienteDAO → PostgreSQL → clientes.jsp (HTML)
Navegador → /api/clientes  → ClienteApiServlet  → ClienteDAO → PostgreSQL → JSON (per a l'index.html d'Apache)
```

`ClienteDAO` i `Cliente` són compartits pels dos Servlets — la lògica d'accés a dades està escrita una sola vegada.
