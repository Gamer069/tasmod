package me.illia.tasmod.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;

public class TasScreen extends Screen {
	@Override
	public void init() {
		this.buttons.add(new ButtonWidget(1, 0, 0, I18n.translate("menu.tasmod.create_tas")));
		super.init();
	}
}
