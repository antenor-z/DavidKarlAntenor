FINAL ITERATION
---------------

* The team will make a video or videos showing the game in action.
* [DONE] The project must have a .doc file called "leiame.doc" indicating each member name and id (matrícula), which class the Main function is located and the presentation video link. This file must be inside projet.
* [DONE] The name of the project is the id of one member or a composition of the ids of each member id. For example: "2011241".
* What will be sent is:
	- Project's .zip using the Eclipe's archive option.
	- 4th interation report (pdf).
	- Video (Google Drive link on "leiame.doc").

PRESENTATION VIDEO REQUIREMENTS
-------------------------------

* A recorded video call with all members of the team. Everyone with cam. Can be several videos recorded in different days.
* The video needs to cover specifics of the gameplay that the professor will put on Moodle.

OBSERVER
--------

Putting information on two parts of the code is prone to bugs.

Event Button ---> CONTROLLER ---> MODEL ---> update ---> note ---> VIEW ---> gets MODEL ---> repaint()

- A button is pushed.
- Callback function (controller) is called.
- Something on gameEvent (facade) is called.
- The Model is modified
- gameState's update() notify every view.
- View gets updated info from gameState.
- view calls repaint()

TODOs
-----
1) When a player needs to pay rent (company or land) and doesn't have money, the game must ask the player which lands/companies to sell. Repeat until the player has enought money. If this is not sufficient set player.bankrupt to true.
2) There are some events that the callback class is declared in-place. It's better to move these classes to Controller.
3) Use the observer.
4) Record the video.
5) Write fourth report.
