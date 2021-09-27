### **create** *database* *filepath*    
Создает новую базу данных с именем *database*, которая будет
храниться в файле *filepath*.
  
*database* - строка без пробелов, имя базы данных, с помощью него можно
обращаться к базе данных внутри интерфейса  
*filepath* - путь к файлу, в который будут сохраняться данные из базы. 
На момент вызова команды **create** файла не должно существовать, он 
создаётся сам.  
  
Возможные ошибки:  
**database '*database*' already exist** - существует открытая база 
данных с именем *database*.  
Могут помочь команды: **[close](./close.md)**, **[Close](./Close.md)**, **[delete](./delete.md)**
  
**file '*filepath*' already exist** - существует файл с именем *filepath*.  
Может помочь команда **[open](./open.md)**.