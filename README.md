# Repo voor Sorteer en inpak robot

## Compile requirements
Om het project te kunnen compilen moet je een paar extra bestanden downloaden. Hieronder is te zien welke er nodig zijn
en hoe ze te verkrijgen. Wanneer je ze hebt voeg ze toe aan je java project als modules/libraries.

### backend.Database connectie
Voor de database connectie heb je de Connector/J nodig van deze site: http://dev.mysql.com/downloads/connector/j/. Deze
geeft helaas een installer op zichzelf dus installeer de web versie (het kleinere bestand). 

Wanneer de installatie compleet is ga naar =>'Add ...' => 'MySQL Connectors' => 'Connector/J'. Dit downloadt de
.jar file wat opgeslagen wordt in C:\Program Files (x86)\MySQL\Connector J 8.0

### Java-Arduino connectie
Voor deze connectie gebruiken we de jSerialComm library. Hiervoor moet je de meest recente 
[release](https://github.com/Fazecast/jSerialComm/releases) downloaden. Deze kun je opslaan waar je maar wil.

## commit regels
### commit message
Begin een commit message altijd met 1 van de volgende woorden:
* Add
* Change
* Remove

Probeer hierna een kort maar duidelijke zin te maken die beschrijft wat de commit inhoud.

### Master/DEV/feature branch
<b> Commit nooit direct in de master branch maar volg de rangorder. </b>

Wanneer je iets nieuws wil toevoegen maak je een eigen feature aan:

Create branch => noem hem: "feature/\<naam van je feature>" (doordat je "feature/" ervoor zet, word het in een apararte 'map' gegooit)