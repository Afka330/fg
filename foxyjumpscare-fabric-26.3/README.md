# Foxy Jumpscare — Fabric port (Minecraft Java 26.3)

Порт NeoForge-мода Foxy Jumpscare (автор Daviduwur, лицензия MIT) на Fabric.
Клиентский мод. Нужны Fabric Loader >= 0.19.3 и Fabric API (0.162.0+26.3 или новее для 26.3).

Сборка:
1. Установить JDK 25.
2. В папке проекта: `gradle wrapper --gradle-version 9.7.1`, затем `./gradlew build`
   (на Windows `gradlew.bat build`).
3. Готовый jar: build/libs/FoxyJumpscare-fabric-1.1.0+26.3.jar (не *-sources.jar)

Конфиг: config/jumpscaremod.properties, ключ chance (1 к X в секунду, по умолчанию 10000).
Для проверки поставь chance=1.

## Без установки Java (через GitHub)
1. Создай репозиторий на github.com, загрузи в него содержимое этой папки (вместе с .github).
2. Вкладка Actions -> build -> Run workflow (или просто подожди, запустится сам после загрузки).
3. Когда завершится, открой запуск и скачай архив FoxyJumpscare-fabric-26.3 в разделе Artifacts. Внутри jar для папки mods.
