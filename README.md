MobicaNavigate
==============


##1. Szczegóły techniczne.

- Program Mobica Navigate został napisany dla telefonów z systemem Android od API 10(v. 2.3.3) do API 17(v. 4.2).
- Jako IDE zastosowałem Android Studio (v. 0.1.9) 
- Do mapy wykorzystałem Google Maps Android API v2
- Aby była możliwość korzystania z map trzeba ściągnąć z SDK "Google Play services" znajdującym się w Extras (oraz podpiąć moduł do projektu)
- Przy kompilowaniu kodu należy pamiętać o własnym kluczu dla aplikacji. Pomoc: https://developers.google.com/maps/documentation/android/start?hl=pl#obtaining_an_api_key

##2. Struktura oraz opis aplikacji.

###Ogólne:

W aplikacji został zdefiniowany własny temat(styl), dla aktywności jak i dla Dialogów. Aplikacja występuje w 2 językach, w języku polskim oraz angielskim do wyboru. Aktywności występują tylko w orientacji portretowej. Możliwy jest wybór wśród 4 biur mobica w Polsce. Posiada zapis ostatnich zapamiętanych decyzji przez użytkownika taki jak: 

-	pierwsze uruchomienie aplikacji,
-	wybór języka,
-	wybrane miasto,
-	ustawienie kamery, 

zapis następuje dzięki SharedPreferences. Podczas pierwszego uruchomienia programu wyświetla się ChoiceDialog z wyborem języka jakiego chcemy używać. Przy próbie włączenia mapy bez włączonego GPS'a oraz internetu aplikacja spyta się poprzez ChoiceDialog o to, czy na pewno chcemy uruchomić mape. Z każdego miejsca aplikacji klawiszem cofania możemy wyjść z aktywności/aplikacji. W klasie z mapą są obsłużone metody onResume oraz onPause wyłączające/włączające nasłuchiwanie z GPS. Do przejścia między dwoma aktywnościami jest nałożony efekt (Jeśli nie widzimy to musimy włączyć: Ustawienia -> Wyświetlacz -> Animacje ->Wszystkie animacje).


###Dialogi:
####ChoiceDialog -  z 2 klawiszami z wyborem
-	podajemy wyświetlany tekst
-	jest konieczność podania listenera dla przycisku akceptacji
-	opcjonalne jest podanie listenera dla przycisku odrzucenia (gdy nie podamy dialog jest wyłączany)
-	możliwość edycji napisu dla przycisków

####ConfirmDialog  - z 1 klawiszem akceptacji
-	nadajemy jedynie wyświetlany tekst

###Aktywności:
####SplashScreen 
- pojawia się wraz z uruchomieniem aplikacji. 
- W każdej chwili mamy możliwość wyłączenia całej aplikacji. 
- Trwa 2 sekundy. 

####MenuActivity 
-	wybrania biura do którego aplikacja ma nas pokierować (za pomocą strzałek możemy zmienić biuro)
-	uruchomienia mapy
-	szybkiego dostępu do ustawień GPS
-	szybkiego dostępu do ustawień Internetu
-	zmiany języka na polski oraz na angielski (po kliknięciu klawisza, automatycznie się odświeża)
-	dostęp do zakładki "O autorze"
-	wyjście
-	po włączeniu mapy poprzez putExtra wysyła informacje o położeniu biura którego wybraliśmy
-	został dodany ScrollView w wypadku dla telefonów z mniejszym ekranem

####AuthorActivity
-	wyświetla informacje o autorze
-	do wyświetlenia dłuższego tekstu zostało zastosowane marquee (służący do przesuwania tekstu)
-	aktywność posiada mniejsze wymiary niż inne aktywności ( na zrzutach ekranu będzie to widoczne)
-	dla telefonów poniżej API 14 będzie widoczny BLUR_BEHIND efekt, który jest widoczny na screenie.

####MapActivity
-	na mapie są 2 markery:
	nasza pozycja pobierana z GPS
	wybrane przez nas biuro
-	mamy ukazaną najkrótszą drogę którą należy przebyć by dojść do biura (dzięki klasie GMapV2Direction)
-	jeśli będziemy poruszać się inną drogą może nam ukazać się nowa droga, stara droga się usunie (usuwa się również w przypadku przebytej drogi)
-	mamy możliwość 2 widoków które możemy wybrać po naciśnięciu klawisza opcji:
	kamera która pokazuje wycentrowaną pozycję w której się znajdujemy
	kamera z widoczną naszą pozycją oraz wybranego przez nas biura (w zależności jak się poruszamy mapa się 'zwiększa lub pomniejsza')
-	jeśli będziemy się znajdować w pobliżu biura(do 20m) to wyświetli się komunikat o dotarciu na miejsce
-	na dole mamy widoczny dystans od nas do biura w linii prostej

###Pozostałe:
####GMapV2Direction
-	klasa dzięki której pobieram drogę do mapy
-	przez httpClienta pobieramy plik xml z drogą
-	został dodany AsyncTask(naprawiał błąd z NetworkOnMainThreadException)

####Office
-	jest to model, który posiada informacje do zlokalizowania biura (nazwa, szerokość geograficzna, długość geograficzna)

####Constants
-	przechowywane są różne hardcode

####NetworkService
-	sprawdza czy jest aktywne połączenie z internetem
-	sprawdza czy jest aktywne połączenie z GPS

##3. Zrzuty ekranów.


![SplashScreen](http://brokenworld.hostzi.com/mobicanavigate/SplashScreen.png)

Rysunek 1 SplashScreen

![FirstRun](http://brokenworld.hostzi.com/mobicanavigate/FirstRun.png)

Rysunek 2 Pierwsze uruchomienie

![Menu_pl](http://brokenworld.hostzi.com/mobicanavigate/Menu_pl.png)

Rysunek 3 Menu Szczecin

![MenuLodz](http://brokenworld.hostzi.com/mobicanavigate/MenuLodz.png)

Rysunek 4 Menu Łódź

![Menu_eng](http://brokenworld.hostzi.com/mobicanavigate/Menu_eng.png)

Rysunek 5 Menu po angielsku

![Author_pl](http://brokenworld.hostzi.com/mobicanavigate/Author_pl.png)

Rysunek 6 Autor

![Author_eng](http://brokenworld.hostzi.com/mobicanavigate/Author_eng.png)

Rysunek 7 Autor po angielsku

![Confirm_pl](http://brokenworld.hostzi.com/mobicanavigate/Confirm_pl.png)

Rysunek 8 Komunikat

![Confirm_eng](http://brokenworld.hostzi.com/mobicanavigate/Confirm_eng.png)

Rysunek 9 Komunikat po angielsku

![Map](http://brokenworld.hostzi.com/mobicanavigate/Map.png)

Rysunek 10 Mapa

![MapMenu](http://brokenworld.hostzi.com/mobicanavigate/MapMenu.png)

Rysunek 11 Mapa z menu

![End](http://brokenworld.hostzi.com/mobicanavigate/End.png)

Rysunek 12 Komunikat przy dotarciu do biura

##4. Autor.
###Miłosz Skalski
