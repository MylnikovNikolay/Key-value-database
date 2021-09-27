### **open** *database* *filepath*  
Создает базу данных *database*, загружая данные из файла *filepath*  
  
*database* - строка без пробелов, имя базы данных, можно выбрать любое не занятое другой
открытой базой данных  
*filepath* - имя существующего файла, из которого загрузятся данные в базу  

Возможные ошибки:  
**database '*database*' already exist** - существует открытая база
данных с именем *database*.  
Могут помочь команды: **[close](./close.md)**, **[Close](./Close.md)**, **[delete](./delete.md)**  
  
**file doesn't exist** - не существует файла *filepath*.  
Может помочь команда **[create](./create.md)**.
