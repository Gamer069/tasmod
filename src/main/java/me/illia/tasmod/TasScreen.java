package me.illia.tasmod;

import com.google.common.io.ByteSource;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ListWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class TasScreen extends Screen {
	private static final Identifier BACKGROUND_TEXTURE = new Identifier("textures/gui/options_background.png");
	private int selected = -1;

	private ButtonWidget selectBtn;
	private ButtonWidget renameBtn;
	private ButtonWidget deleteBtn;
	private ButtonWidget recreateBtn;
	private ArrayList<Tas> tases;

	@Override
	public void init() {
		this.buttons.add(new ButtonWidget(1, this.width / 2 + 4, this.height - 52, 150, 20, I18n.translate("menu.tasmod.create_tas")));
		this.buttons.add(this.selectBtn = new ButtonWidget(2, this.width / 2 - 154, this.height - 52, 150, 20, I18n.translate("menu.tasmod.select_tas")));
		this.buttons.add(this.renameBtn = new ButtonWidget(3, this.width / 2 - 154, this.height - 28, 72, 20, I18n.translate("menu.tasmod.rename_tas")));
		this.buttons.add(this.deleteBtn = new ButtonWidget(4, this.width / 2 - 76, this.height - 28, 72, 20, I18n.translate("menu.tasmod.delete_tas")));
		this.buttons.add(this.recreateBtn = new ButtonWidget(5, this.width / 2 + 4, this.height - 28, 72, 20, I18n.translate("menu.tasmod.recreate_tas")));
		this.buttons.add(new ButtonWidget(6, this.width / 2 + 82, this.height - 28, 72, 20, I18n.translate("gui.cancel")));
		super.init();

		this.selectBtn.active = false;
		this.renameBtn.active = false;
		this.deleteBtn.active = false;
		this.recreateBtn.active = false;
	}

	@Override
	public void render(int mouseX, int mouseY, float tickDelta) {
		renderBackground();
		super.render(mouseX, mouseY, tickDelta);
	}

	@Override
	protected void buttonClicked(ButtonWidget button) {
		if (button.id == 1) {
			client.setScreen(new CreateTasScreen());
		}

		if (button.id == 2) {
			ClientState.SELECTED_TAS = selected;
		}

		if (button.id == 6) {
			client.closeScreen();
		}


		super.buttonClicked(button);
	}


	@Environment(EnvType.CLIENT)
	class TasListWidget extends ListWidget {
		public TasListWidget(MinecraftClient minecraftClient) {
			super(minecraftClient, TasScreen.this.width, TasScreen.this.height, 32, TasScreen.this.height - 64, 36);
		}

		@Override
		protected int getEntryCount() {
			return TasScreen.this.tases.size();
		}

		@Override
		protected void selectEntry(int index, boolean doubleClick, int lastMouseX, int lastMouseY) {
			TasScreen.this.selected = index;
			boolean bl = TasScreen.this.selected >= 0 && TasScreen.this.selected < this.getEntryCount();
			TasScreen.this.selectBtn.active = bl;
			TasScreen.this.deleteBtn.active = bl;
			TasScreen.this.renameBtn.active = bl;
			TasScreen.this.recreateBtn.active = bl;
			if (doubleClick && bl) {
				TasRunner.runTas(tases.get(TasScreen.this.selected));
			}
		}

		@Override
		protected boolean isEntrySelected(int index) {
			return index == TasScreen.this.selected;
		}

		@Override
		protected int getMaxPosition() {
			return TasScreen.this.tases.size() * 36;
		}

		@Override
		protected void renderBackground() {
			TasScreen.this.renderBackground();
		}

		@Override
		protected void renderEntry(int index, int x, int y, int rowHeight, int mouseX, int mouseY) {
//			LevelSummary levelSummary = (LevelSummary)TasScreen.this.worlds.get(index);
//			String string = levelSummary.getDisplayName();
//			if (StringUtils.isEmpty(string)) {
//				string = TasScreen.this.defaultWorldName + " " + (index + 1);
//			}
//
//			String string2 = levelSummary.getFileName();
//			string2 = string2 + " (" + TasScreen.this.dateFormat.format(new Date(levelSummary.getLastPlayed()));
//			string2 = string2 + ")";
//			String string3 = "";
//			if (levelSummary.requiresConversion()) {
//				string3 = TasScreen.this.mustConvertText + " " + string3;
//			} else {
//				string3 = TasScreen.this.gameModeTexts[levelSummary.getGameMode().getId()];
//			}

//			TasScreen.this.drawWithShadow(TasScreen.this.textRenderer, string, x + 2, y + 1, 16777215);
//			TasScreen.this.drawWithShadow(TasScreen.this.textRenderer, string2, x + 2, y + 12, 8421504);
//			TasScreen.this.drawWithShadow(TasScreen.this.textRenderer, string3, x + 2, y + 12 + 10, 8421504);
		}
	}

}