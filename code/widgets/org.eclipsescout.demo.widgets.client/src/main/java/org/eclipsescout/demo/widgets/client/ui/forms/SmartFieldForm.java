/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.widgets.client.ui.forms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractProposalField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipsescout.demo.widgets.client.services.lookup.LocaleLookupCall;
import org.eclipsescout.demo.widgets.client.services.lookup.RemoteLocaleLookupCall;
import org.eclipsescout.demo.widgets.client.services.lookup.UserContentListLookupCall;
import org.eclipsescout.demo.widgets.client.services.lookup.UserContentTreeLookupCall;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.CloseButton;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseAutoExpandAllField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseHierarchyField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseMaxRowCountField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.GetValueField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.ListEntriesField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.ListSmartField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.TreeEntriesField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.TreeSmartField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DefaultSmartField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DisabledField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DisabledSmartFieldField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.MandatoryField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.MandatorySmartfieldField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.SmartFieldWithListContentField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.SmartFieldWithTreeContentField;
import org.eclipsescout.demo.widgets.client.ui.forms.SmartFieldForm.MainBox.SampleContentButton;
import org.eclipsescout.demo.widgets.client.ui.template.formfield.AbstractUserTreeField;
import org.eclipsescout.demo.widgets.shared.services.code.ColorsCodeType;
import org.eclipsescout.demo.widgets.shared.services.code.EventTypeCodeType;
import org.eclipsescout.demo.widgets.shared.services.code.IndustryICBCodeType;
import org.eclipsescout.demo.widgets.shared.services.code.IndustryICBCodeType.ICB9000.ICB9500.ICB9530.ICB9537;

public class SmartFieldForm extends AbstractForm implements IPageForm {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(SmartFieldForm.class);

  public SmartFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SmartField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the BrowseAutoExpandAllField
   */
  public BrowseAutoExpandAllField getBrowseAutoExpandAllField() {
    return getFieldByClass(BrowseAutoExpandAllField.class);
  }

  /**
   * @return the BrowseHierarchyField
   */
  public BrowseHierarchyField getBrowseHierarchyField() {
    return getFieldByClass(BrowseHierarchyField.class);
  }

  /**
   * @return the BrowseMaxRowCountField
   */
  public BrowseMaxRowCountField getBrowseMaxRowCountField() {
    return getFieldByClass(BrowseMaxRowCountField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  /**
   * @return the DisabledSmartFieldField
   */
  public DisabledSmartFieldField getDisabledSmartFieldField() {
    return getFieldByClass(DisabledSmartFieldField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  /**
   * @return the DefaultField
   */
  public DefaultField getDefaultField() {
    return getFieldByClass(DefaultField.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  /**
   * @return the MandatoryField
   */
  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  /**
   * @return the MandatorySmartfieldField
   */
  public MandatorySmartfieldField getMandatorySmartfieldField() {
    return getFieldByClass(MandatorySmartfieldField.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  /**
   * @return the DefaultSmartField
   */
  public DefaultSmartField getDefaultSmartField() {
    return getFieldByClass(DefaultSmartField.class);
  }

  /**
   * @return the GetCheckedKeysField
   */
  public GetValueField getGetValueField() {
    return getFieldByClass(GetValueField.class);
  }

  /**
   * @return the ListSmartField
   */
  public ListSmartField getListSmartField() {
    return getFieldByClass(ListSmartField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TreeEntriesField
   */
  public ListEntriesField getListEntriesField() {
    return getFieldByClass(ListEntriesField.class);
  }

  /**
   * @return the SmartFieldWithListContentField
   */
  public SmartFieldWithListContentField getSmartFieldWithListContentField() {
    return getFieldByClass(SmartFieldWithListContentField.class);
  }

  /**
   * @return the SmartFieldWithTreeContentField
   */
  public SmartFieldWithTreeContentField getSmartFieldWithTreeContentField() {
    return getFieldByClass(SmartFieldWithTreeContentField.class);
  }

  /**
   * @return the TreeEntriesField
   */
  public TreeEntriesField getTreeEntriesField() {
    return getFieldByClass(TreeEntriesField.class);
  }

  /**
   * @return the SampleContentButton
   */
  public SampleContentButton getSampleContentButton() {
    return getFieldByClass(SampleContentButton.class);
  }

  /**
   * @return the TreeSmartField
   */
  public TreeSmartField getTreeSmartField() {
    return getFieldByClass(TreeSmartField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 3;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10.0)
      public class ShowDefaultValueButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_LINK;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ShowValue");
        }

        @Override
        protected String getConfiguredTooltipText() {
          return TEXTS.get("ShowValueOfDefaultField");
        }

        @Override
        protected String getConfiguredKeyStroke() {
          return "Ctrl-Alt-P";
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          MessageBoxes.createOk().withBody(getDefaultField().getValue() + "").show();
        }
      }

      @Order(15.0)
      public class SwitchLookupCall extends AbstractButton {

        private boolean m_localLookupCall = true;

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_LINK;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("SwitchToRemote");
        }

        @Override
        protected String getConfiguredTooltipText() {
          return TEXTS.get("SwitchToRemoteTooltip");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          m_localLookupCall = !m_localLookupCall;
          if (m_localLookupCall) {
            getDefaultField().setLookupCall(new LocaleLookupCall());
            setLabel(TEXTS.get("SwitchToRemote"));
            setTooltipText(TEXTS.get("SwitchToRemoteTooltip"));
          }
          else {
            getDefaultField().setLookupCall(new RemoteLocaleLookupCall());
            setLabel(TEXTS.get("SwitchToLocal"));
            setTooltipText(TEXTS.get("SwitchToLocalTooltip"));
          }
          LOG.debug("Switched lookup-call of DefaultField to " + (m_localLookupCall ? "local" : "remote") + " instance");
        }
      }

      @Order(20.0)
      public class SmartFieldWithListContentField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("EmptyString");
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("SmartFieldWithListContent"));
        }
      }

      @Order(30.0)
      public class DefaultField extends AbstractSmartField<Locale> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected Class<? extends ILookupCall<Locale>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<Locale>>) /*Remote*/LocaleLookupCall.class;
        }
      }

      @Order(40.0)
      public class MandatoryField extends AbstractSmartField<Color> {

        @Override
        protected Class<? extends ICodeType<?, Color>> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          Color color = getValue();
          if (color == null) {
            setBackgroundColor(null);
          }
          else {
            String hex = Integer.toHexString(color.getRGB()).substring(2);
            setBackgroundColor(hex);
          }
        }
      }

      @Order(50.0)
      public class DisabledField extends AbstractSmartField<Color> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType<?, Color>> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(new Color(255, 255, 255));
        }
      }

      @Order(60.0)
      public class SmartFieldWithTreeContentField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("EmptyString");
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("SmartFieldWithTreeContent"));
        }
      }

      @Order(70.0)
      public class DefaultSmartField extends AbstractSmartField<Long> {

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(80.0)
      public class MandatorySmartfieldField extends AbstractSmartField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(90.0)
      public class DisabledSmartFieldField extends AbstractSmartField<Long> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(ICB9537.ID);
        }
      }

      @Order(100.0)
      public class ProposalFieldWithListContentField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("EmptyString");
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("ProposalFieldWithListContent"));
        }
      }

      @Order(110.0)
      public class DefaultProposalField extends AbstractProposalField<Long> {

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return EventTypeCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(120.0)
      public class MandatoryProposalField extends AbstractProposalField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return EventTypeCodeType.class;
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(130)
      public class DisabledProposalField extends AbstractProposalField<Long> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return EventTypeCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("Public"));
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 3;
      }

      @Order(10.0)
      public class ListSmartField extends AbstractSmartField<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ListSmartField");
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<String>>) UserContentListLookupCall.class;
        }
      }

      @Order(20.0)
      public class GetValueField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("GetValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return SmartFieldForm.MainBox.ConfigurationBox.ListSmartField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          if (newMasterValue != null) {
            setValue(newMasterValue.toString());
          }
          else {
            setValue(null);
          }
        }
      }

      @Order(30.0)
      public class ListEntriesField extends AbstractUserTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ListContent");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          List<Node> nodes = parseFieldValue(false);
          ArrayList<LookupRow<String>> rows = new ArrayList<LookupRow<String>>();
          addNodesToLookupRows(nodes, rows);

          ((UserContentListLookupCall) getListSmartField().getLookupCall()).setLookupRows(rows);
          try {
            getListSmartField().initField();
          }
          catch (ProcessingException e) {
            e.printStackTrace();
          }
        }
      }

      @Order(40.0)
      public class BrowseMaxRowCountField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseMaxRowCount");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Integer getConfiguredMinValue() {
          return 0;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getListSmartField().setBrowseMaxRowCount(NumberUtility.nvl(getValue(), 100));
          getTreeSmartField().setBrowseMaxRowCount(NumberUtility.nvl(getValue(), 100));
        }
      }

      @Order(50.0)
      public class Placeholder1Field extends AbstractPlaceholderField {
      }

      @Order(70.0)
      public class TreeSmartField extends AbstractSmartField<String> {

        @Override
        protected boolean getConfiguredBrowseAutoExpandAll() {
          return false;
        }

        @Override
        protected boolean getConfiguredBrowseHierarchy() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeSmartField");
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<String>>) UserContentTreeLookupCall.class;
        }
      }

      @Order(80.0)
      public class GetValueTreeSmartFieldField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("GetValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return SmartFieldForm.MainBox.ConfigurationBox.TreeSmartField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          if (newMasterValue != null) {
            setValue(newMasterValue.toString());
          }
          else {
            setValue(null);
          }
        }
      }

      @Order(90.0)
      public class TreeEntriesField extends AbstractUserTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeContent");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          List<Node> nodes = parseFieldValue(true);
          List<LookupRow<String>> rows = new ArrayList<LookupRow<String>>();

          addNodesToLookupRows(nodes, rows);
          ((UserContentTreeLookupCall) getTreeSmartField().getLookupCall()).setLookupRows(rows);
        }
      }

      @Order(100.0)
      public class BrowseHierarchyField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseHierarchy");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTreeSmartField().setBrowseHierarchy(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTreeSmartField().isBrowseHierarchy());
        }
      }

      @Order(110.0)
      public class BrowseAutoExpandAllField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseAutoExpandAll");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTreeSmartField().setBrowseAutoExpandAll(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTreeSmartField().isBrowseAutoExpandAll());
        }
      }

      @Order(120.0)
      public class PlaceholderField extends AbstractPlaceholderField {

        @Override
        protected int getConfiguredGridH() {
          return 6;
        }

      }
    }

    @Order(30.0)
    public class SampleContentButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SampleContent");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        ListEntriesField listEntries = getListEntriesField();
        listEntries.setValue(TEXTS.get("ListBoxUserContent"));

        TreeEntriesField treeEntries = getTreeEntriesField();
        treeEntries.setValue(TEXTS.get("TreeUserContent"));
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}