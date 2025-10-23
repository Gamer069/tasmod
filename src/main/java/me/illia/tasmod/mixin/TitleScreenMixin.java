package me.illia.tasmod.mixin;

import me.illia.tasmod.TasScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ListWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Formatting;
import net.minecraft.world.level.storage.LevelSummary;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Date;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	@Mutable
	@Shadow
	private float minecraftRandomNumber;

	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		this.minecraftRandomNumber = 0.9E-4f;
	}

	@Inject(at = @At("TAIL"), method = "initWidgetsNormal")
	private void initWidgets(int y, int spacingY, CallbackInfo info) {
		this.buttons.add(new ButtonWidget(3, this.width / 2 - 120 - 5, y, 20, 20, I18n.translate("menu.tasmod.tas")));
	}

	@Inject(at = @At("TAIL"), method = "buttonClicked")
	protected void buttonClicked(ButtonWidget button, CallbackInfo info) {
		if (button.id == 3) {
			this.client.setScreen(new TasScreen());
		}
	}
}
