package com.github.zxyphp.bornuntil.services;

import com.github.zxyphp.bornuntil.BornUntilStatusWidget;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhangxingyu
 */
public class MyProjectService implements ProjectActivity {
    @Override
    public @Nullable Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        return CompletableFuture.runAsync(() -> {
            StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
            if (statusBar != null) {
                statusBar.addWidget(new BornUntilStatusWidget(project), "after Position", project);
            }
        });
    }
}
