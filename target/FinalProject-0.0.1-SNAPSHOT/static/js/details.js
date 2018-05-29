
document.
function Model(key, template, data) {
    this.key = key;
    this.template = ko.observable(template);
    this.data = data;
}

var viewModel = {
    models: ko.observableArray([
        new Model("user", "userTmpl", { first: "Bob", last: "Smith" }),
        new Model("item", "itemTmpl", { name: "MyItem", description: "Here are some details" })
    ]),
    selectedModel: ko.observable()
};


ko.applyBindings(viewModel);