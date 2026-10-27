package co.edu.taller.creational;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExecutiveReport {
    private final String title;
    private final String period;
    private final List<String> sections;

    private ExecutiveReport(Builder builder) {
        this.title = builder.title;
        this.period = builder.period;
        this.sections = Collections.unmodifiableList(new ArrayList<>(builder.sections));
    }

    public String title() { return title; }
    public String period() { return period; }
    public List<String> sections() { return sections; }

    public static class Builder {
        private String title;
        private String period;
        private final List<String> sections = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder period(String period) {
            this.period = period;
            return this;
        }

        public Builder addSection(String section) {
            if (section != null && !section.isBlank()) {
                this.sections.add(section);
            }
            return this;
        }

        public ExecutiveReport build() {
            if (title == null || title.isBlank() || period == null || period.isBlank()) {
                throw new IllegalStateException("Título y Periodo son obligatorios");
            }
            return new ExecutiveReport(this);
        }
    }
}