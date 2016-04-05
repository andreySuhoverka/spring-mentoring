По логам можно видеть, что Book имеет scope=prototype
Если удалить scope в appContext.xml у Book, то применится default scope=singleton и bean Book создастся один раз и будет уже инжектаться.