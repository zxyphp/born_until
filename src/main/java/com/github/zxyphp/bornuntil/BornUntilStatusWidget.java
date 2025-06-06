package com.github.zxyphp.bornuntil;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidget.TextPresentation;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;

public class BornUntilStatusWidget implements StatusBarWidget, TextPresentation {

    private final Project project;

    public BornUntilStatusWidget(Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String ID() {
        return "BornUntilWidget";
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation() {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {}

    @Override
    public void dispose() {}

    @NotNull
    @Override
    public String getText() {
        String birth = PropertiesComponent.getInstance().getValue("bornuntil.birthday", "1993-09-29");
        try {
            Period age = Period.between(LocalDate.parse(birth), LocalDate.now());
            return String.format("🎂 %d岁%d月%d天", age.getYears(), age.getMonths(), age.getDays());
        } catch (Exception e) {
            return "🎂 出生日期错误";
        }
    }

    @Nullable
    public String getMaxPossibleText() {
        return "🎂 999岁99月99天";
    }

    @Nullable
    @Override
    public String getTooltipText() {
        return "你的实时年龄";
    }

    @Nullable
    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return null;
    }

    @Override
    public float getAlignment() {
        return 0;
    }
}
