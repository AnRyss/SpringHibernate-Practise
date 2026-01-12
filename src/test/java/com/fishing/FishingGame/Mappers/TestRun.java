package com.fishing.FishingGame.Mappers;

public class TestRun {
    public static void main(String[] args) {
        try {
            System.out.println("=== Запускаем тест вручную ===");

            // Создаем объекты
            com.fishing.FishingGame.Mappers.PlayerMapperTest test =
                    new com.fishing.FishingGame.Mappers.PlayerMapperTest();

            // Вызываем методы
            test.setUp(); // если есть
            test.toEntity_ShouldMapDomainToEntity();

            System.out.println("✅ Тест выполнен успешно!");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
