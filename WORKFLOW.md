# Branch Workflow

## Descrizione

La metodologia di gestione del repository che useremo è una derivazione del **Feature branch Workflow**.
In questa fase del progetto in cui il lavoro implica la stesura di testi e non l'implementazione di feature, useremo 5
branch per gestire il lavoro:

1. master
2. nicolò
3. salvatore
4. andrea
5. gianluigi

Nel branch "master" risiedera la code base principale del nostro progetto.
Ognuno dei membri del team invece svilupperà il progetto lavorando nel proprio branch.
Quando un branch e pronto ad essere mergiato nella codebase principale l'utente che lo ha sviluppato solleverà una pull
request.
Alla pull request seguirà la revisione del codice da parte di almeno un'altro membro del team che darà l'approvazione.
una volta mergiato il branch personale nel "master" si procederà a cancellare il proprio branch e ricrearlo partendo dal
branch "master".

## Linee guida

Quelle che seguono sono delle linee guida per l'utilizzo di questo workflow.

1. posizioniamo sul branch dal quale voglia creare il nuovo branch (per esempio: "master")

```bash
git pull -p origin
git checkout master
```

3. creiamo il nostro nuovo branch

```bash
git checkout -b nome_del_membro_del_team
```

4. lavoriamo sul nostro codice come di consueto ma pushando sul server remoto i vari commit del nostro branch in modo da
   renderli disponibili agli altri membri del team

```bash
git status
git add
git commit -m "commento del commit"
git push origin nome_del_membro_del_team
```

5. quando crediamo che il nostro lavoro si pronto per essere mergiato, oppure vogliamo sottoporre il nostro codice alla
   review di un altro membro del tema andiamo su github e creiamo una pull request.\
6. grazie alla review del nostro compagno arriviamo al merge sul branch master e alla cancellazione del branch "
   nome_del_membro_del_team"
7. aggiorniamo il repo locale riposizionamoci sul ramo di feature sul quale stiamo lavorando e aprimo un nostro nuovo
   branch per il prossimo sprint di lavoro

```bash
git pull -p origin
git checkout master
git checkout -b nome_del_membro_del_team
```

8. seguiamo la revisione ed il merge.
9. ricominciamo dal punto 1 su una nuova issue