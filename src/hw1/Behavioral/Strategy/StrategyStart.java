package hw1.Behavioral.Strategy;

interface VideoCardSettings {
    void setSettings();
}

class QualitySettings implements VideoCardSettings {
    @Override
    public void setSettings() {
        System.out.println("Режим работы видеокарты: качество");
    }
}

class PerformanceSettings implements VideoCardSettings {
    @Override
    public void setSettings() {
        System.out.println("Режим работы видеокарты: производительность");
    }
}

class BalancedSettings implements VideoCardSettings {
    @Override
    public void setSettings() {
        System.out.println("Режим работы видеокарты: сбалансированный");
    }
}


class VideoCard {
    private VideoCardSettings videoCardSettings;

    public void setVideoCardSettings(VideoCardSettings videoCardSettings) {
        this.videoCardSettings = videoCardSettings;
    }

    public void getCurrentSettings() {
        System.out.print("Текущая настройка видеокарты: ");
        videoCardSettings.setSettings();
    }
}

public class StrategyStart {
    public static void main(String[] args) {
        VideoCard videoCard = new VideoCard();

        videoCard.setVideoCardSettings(new QualitySettings());
        videoCard.getCurrentSettings();

        videoCard.setVideoCardSettings(new PerformanceSettings());
        videoCard.getCurrentSettings();

        videoCard.setVideoCardSettings(new BalancedSettings());
        videoCard.getCurrentSettings();
    }
}
