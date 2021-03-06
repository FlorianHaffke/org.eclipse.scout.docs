jswidgets.FormForm = function() {
  jswidgets.FormForm.parent.call(this);
};
scout.inherits(jswidgets.FormForm, scout.Form);

jswidgets.FormForm.prototype._init = function(model) {
  jswidgets.FormForm.parent.prototype._init.call(this, model);

  var bodyGrid = new scout.HorizontalGroupBoxBodyGrid();
  bodyGrid.validate(this.rootGroupBox);

  bodyGrid = new scout.HorizontalGroupBoxBodyGrid();
  bodyGrid.validate(this.widget('DetailBox'));

  this.widget('OpenAsDialogButton').on('click', this._onOpenAsDialogButtonClick.bind(this));
  this.widget('OpenAsViewButton').on('click', this._onOpenAsViewButtonClick.bind(this));
};

jswidgets.FormForm.prototype._jsonModel = function() {
  return scout.models.getModel('jswidgets.FormForm');
};

jswidgets.FormForm.prototype._onOpenAsDialogButtonClick = function(model) {
  var form = scout.create('jswidgets.FormForm', {
    parent: this,
    displayHint: scout.Form.DisplayHint.DIALOG,
    title: 'Dialog'
  });
  this.session.desktop.showForm(form);
};

jswidgets.FormForm.prototype._onOpenAsViewButtonClick = function(model) {
  var form = scout.create('jswidgets.FormForm', {
    parent: this,
    displayHint: scout.Form.DisplayHint.VIEW,
    title: 'View',
    modal: false
  });
  this.session.desktop.showForm(form);
};
