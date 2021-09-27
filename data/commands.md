##Список команд и краткие описания к ним
Более полные описания хранятся в отдельных файлах в папке *data*.
К ним можно перейти, нажав на соответствующую команду  
**Ошибка incorrect input** означает, что введено неверное количество 
аргументов или несуществующее название команды, остальные возможные ошибки
описаны отдельно для каждой функции

**[create](./create.md)** *database* *filepath*    
Создает новую базу данных с именем *database*, которая будет
храниться в файле *filepath*

**[open](./open.md)** *database* *filepath*  
Создает базу данных *database*, загружая данные из файла *filepath*

**[exists](./exist.md)** *database*  
Проверяет, существует ли база данных с именем *database* в списке открытых
баз данных

**[delete](./delete.md)** *database*  
Удаляет базу данных *database* **вместе** с файлом, в котором она хранилась

**[deleteAll](./deleteAll.md)**  
Вызывает **[delete](./delete.md)** для всех открытых баз данных

**[close](./close.md)** *database*  
Закрывает базу данных *database*, сохраняя её содержимое в файл,
который указывался при создании

**[closeAll](./closeAll.md)**  
Вызывает **[close](./close.md)** для всех открытых баз данных

**[Close](./Close.md)** *database*  
Удаляет базу данных *database*, **не** сохраняя содержимое, то есть
в файл не запишутся изменения после последнего вызова

**[CloseAll](./CloseAll.md)**  
Вызывает **[Close](./Close.md)** для всех открытых баз данных

**[merge](./merge.md)** *database1* *database2*  
Добавляет новые и перезаписывает существующие значения из *database2* в
*database1*

**[list](./list.md)**  
Выводит все открытые базы данных и их имена файлов

**[add](./add.md)** *database* *key* *value*  
Добавляет в *database* значение *value* по ключу *key* или перезаписывает
существующее

**[contains](./contains.md)**  *database* *key*  
Проверяет, существует ли в *database* ключ *key*

**[get](./get.md)** *database* *key*  
Выводит значение в *database* по ключу *key*

**[Get](./Get.md)** *database* *value*  
Выводит все ключи из *database*, значение которых совпадает с *value*

**[erase](./erase.md)**  *database* *key*  
Удаляет ключ *key* из *database*

**[Erase](./Erase.md)** *database* *value*  
Удаляет все ключи из *database*, значение в которых равно *value*  
  
**[entries](./entries.md)** *database*  
Выводит все пары *key*-*value* из *database*

**[exit](./exit.md)**  
Завершает работу интерфейса