# blog
1)  cloner et accéder au dossier blog
2)	Avec la ligne de commande lancer mvn clean install
3)	Lancer l’application via l’éditeur inelliJ
4)	Accéder à l’URL   http://localhost:8080/h2 
5)	Cliquer sur connect et copier le script sql suivant :
DROP TABLE IF EXISTS BLOG;
CREATE TABLE BLOG(Id INT PRIMARY KEY, comment VARCHAR(255));
INSERT INTO BLOG VALUES(1, 'comment1');
INSERT INTO BLOG VALUES(2, 'comment2');
INSERT INTO BLOG VALUES(3, 'comment3');
INSERT INTO BLOG VALUES(4, 'comment4');
SELECT * FROM BLOG ORDER BY ID;

Puis cliquer sur run pour créer et alimenter la table BLOG

PS : si vous avez des exceptions due à la base H2, supprimer le dossier h2 sous m2\repository\com\h2database ensuite
refaire mvn clean install et enfin à partir d'IntelliJ click droit maven -> reimport 

