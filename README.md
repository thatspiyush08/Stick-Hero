# Stick-Hero
Stick-Hero is classic Java development game and drawing inspiration from the Mario Universe its background is based on the same.
The Stick Hero Game  offers an engaging adventure where players control the Stickman and collect cherries, with a dynamic stick-extending mechanism,Stickman must skillfully land on platforms and avoid obstacles.The game combines intuitive controls, strategic gameplay, and a vibrant visual style to create an exciting and nostalgic gaming experience reminiscent of beloved classics like Super Mario, providing players with a delightful journey through an imaginative and challenging world.
UML DIAGRAM:
The arrow with diamond head means composition. The arrow means inheritance. The line with no head means composition. These all are the symbols used in UML.
The Stick Hero Game UML diagram offers a detailed depiction of the game's architecture, showcasing the relationships and interactions among essential classes.
The hierarchical structure highlights the inheritance of fundamental properties from the abstract Orientation class by key entities such as Stickman, Cherries, Platform, and Bonus. Notably, Stickman exhibits composition relationships with StickLength and CherriesCollection, emphasizing the encapsulation of critical functionalities. Dependencies are elucidated, with Controls relying on Orientation and Stickman being dependent on GameManager and PowerUp.

Good Coding Practices Used:

~ Serialisation and Deserialisation used for saving and using the game progress.
~ Singleton Design Pattern and various other design patterns used.
~ Name of the variables and methods used are kept such that any programmer will be able to guess their use and working.
~ The flow of the Code is very simple and fluent making the game more interesting and interactive for the users.
~

Features of the Game:

~ Allows players to control a character named stick-hero who moves between platforms.
~ The game includes a reviving feature.
~ The character collects rewards, such as cherries by flipping the player upside down.
~ The game contains graphics, sound effects, and animations to enhance the overall gaming experience like the classic Mario.
~

How To Play

~ Press the TouchPad or Mouse to make sure the stick extends.

~ F key is used to flip the Stick Hero.

~ You can revive the player after falling by using 3 Cherries.

Assumptions To Remember

~ Do not touch on TouchPad  or do mouse click  while Stick-Hero is Moving.

~ If you flip the stickhero on the platform, it will fall and the game will end.

~ When we will press the pause button

