(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["modules-tools-tools-module"],{

/***/ "/Qx1":
/*!*********************************************!*\
  !*** ./src/app/shared/enums/tool-status.ts ***!
  \*********************************************/
/*! exports provided: ToolStatusType */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToolStatusType", function() { return ToolStatusType; });
var ToolStatusType;
(function (ToolStatusType) {
    ToolStatusType["MANUALLY_ADDED"] = "Manually added";
    ToolStatusType["AUTODETECTED"] = "Autodetected";
    ToolStatusType["NORMAL"] = "Normal";
})(ToolStatusType || (ToolStatusType = {}));


/***/ }),

/***/ "0e1l":
/*!***********************************************!*\
  !*** ./src/app/modules/tools/tools.module.ts ***!
  \***********************************************/
/*! exports provided: ToolsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToolsModule", function() { return ToolsModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _container_tools_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./container/tools.component */ "TYcU");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/shared/shared.module */ "PCNd");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./components/tool-form/tool-form.component */ "7K1o");
/* harmony import */ var _components_calculation_calculation_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./components/calculation/calculation.component */ "shSU");










const routes = [{ path: '', component: _container_tools_component__WEBPACK_IMPORTED_MODULE_2__["ToolsComponent"] }];
class ToolsModule {
}
ToolsModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({ type: ToolsModule });
ToolsModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({ factory: function ToolsModule_Factory(t) { return new (t || ToolsModule)(); }, imports: [[_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"].forChild(routes), src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"]]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](ToolsModule, { declarations: [_container_tools_component__WEBPACK_IMPORTED_MODULE_2__["ToolsComponent"], _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_6__["ToolFormComponent"], _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_6__["ToolFormComponent"], _components_calculation_calculation_component__WEBPACK_IMPORTED_MODULE_7__["CalculationComponent"]], imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"]] }); })();
(function () { (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ToolsModule, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
        args: [{
                declarations: [_container_tools_component__WEBPACK_IMPORTED_MODULE_2__["ToolsComponent"], _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_6__["ToolFormComponent"], _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_6__["ToolFormComponent"], _components_calculation_calculation_component__WEBPACK_IMPORTED_MODULE_7__["CalculationComponent"]],
                imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"].forChild(routes), src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"]],
            }]
    }], null, null); })();


/***/ }),

/***/ "7K1o":
/*!***************************************************************************!*\
  !*** ./src/app/modules/tools/components/tool-form/tool-form.component.ts ***!
  \***************************************************************************/
/*! exports provided: ToolFormComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToolFormComponent", function() { return ToolFormComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _sharedEnums__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @sharedEnums */ "k5Aa");
/* harmony import */ var _sharedModels__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @sharedModels */ "TwVa");
/* harmony import */ var src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/shared/enums/tool-status */ "/Qx1");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _shared_components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../../../shared/components/dropdown/dropdown.component */ "4yaq");









const _c0 = function (a0, a1) { return { "text-green-600": a0, "text-red-600": a1 }; };
class ToolFormComponent {
    constructor(fb) {
        this.fb = fb;
        // enums Reaction
        this.reactions = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_2__["StopReactionType"]).map((x) => x[1]);
        // enums Tool
        this.toolTypes = Object.entries(src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__["ToolStatusType"]).map((x) => x[1]);
        // enums Tolerance
        this.toleranceTypes = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_2__["ToleranceEnum"]).map((x) => x[1]);
        this.edit = false;
        this.action = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.cancel = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.submitted = false;
        this.toolForm = this.fb.group({
            id: [null],
            plcId: null,
            name: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            toolNumber: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            numberOfReferenceCycles: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            stopReaction: [false, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            automaticMonitoring: [false, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            calculateReferenceCurve: [false, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            referenceCurveIsCalculated: [false, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            toolStatus: [src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__["ToolStatusType"].MANUALLY_ADDED, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            isActive: false,
            tolerance: this.fb.group({
                type: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
                torqueTolerance: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
                speedTolerance: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            }),
        });
    }
    set data(data) {
        data &&
            ((data === null || data === void 0 ? void 0 : data.tolerance) ? this.toolForm.setValue(data)
                : ((data = Object.assign({}, this.automatic(data))), this.toolForm.setValue(data)));
        data && this.prettyfyEnums(data);
    }
    onCancel() {
        this.cancel.emit();
    }
    // check for erors in form control
    checkErrors(control, tolerance = false) {
        let result;
        switch (tolerance) {
            case true: {
                const node = this.toolForm.get('tolerance').get(control);
                (node.dirty || (this.submitted && !node.dirty)) && !node.valid
                    ? (result = true)
                    : (result = false);
                return result;
            }
            case false: {
                const node = this.toolForm.get(control);
                (node.dirty || (this.submitted && !node.dirty)) && !node.valid
                    ? (result = true)
                    : (result = false);
                return result;
            }
        }
    }
    submit() {
        this.submitted = true;
        this.toolForm.valid &&
            (this.toolForm.patchValue({ plcId: this.plc.id }),
                this.action.emit({ data: this.toolForm.value, edit: this.edit }));
    }
    selected(value, type) {
        switch (type) {
            case 'Tool': {
                this.selectedTool = value;
                this.toolForm.patchValue({ toolStatus: value });
                break;
            }
            case 'Reaction': {
                this.selectedReaction = value;
                this.toolForm.patchValue({ stopReaction: value });
                break;
            }
            case 'Tolerance': {
                this.selectedTolerance = value;
                this.toolForm.patchValue({ tolerance: { type: value } });
                break;
            }
        }
    }
    automatic(data) {
        data.tolerance = new _sharedModels__WEBPACK_IMPORTED_MODULE_3__["Tolerance"](null, null, null);
        data.numberOfReferenceCycles = null;
        data.stopReaction = _sharedEnums__WEBPACK_IMPORTED_MODULE_2__["StopReactionType"].DO_NOTHING;
        return data;
    }
    prettyfyEnums(data) {
        const tolerance = data === null || data === void 0 ? void 0 : data.tolerance;
        tolerance.type &&
            (this.selectedTolerance = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_2__["ToleranceEnum"]).find((x) => x[1] === data.tolerance.type)[1]);
        data.stopReaction &&
            (this.selectedReaction = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_2__["StopReactionType"]).find((x) => x[1] === data.stopReaction)[1]);
        data.toolStatus &&
            (this.selectedTool = Object.entries(src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__["ToolStatusType"]).find((x) => x[1] === data.toolStatus)[1]);
    }
}
ToolFormComponent.ɵfac = function ToolFormComponent_Factory(t) { return new (t || ToolFormComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"])); };
ToolFormComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: ToolFormComponent, selectors: [["tool-form"]], inputs: { plc: "plc", edit: "edit", data: "data" }, outputs: { action: "action", cancel: "cancel" }, decls: 88, vars: 35, consts: [[1, "bg-white", "px-4", "pt-5", "pb-4", "sm:p-6", "sm:pb-4", "sm:max-w-lg", "sm:w-full"], [1, "sm:flex", "sm:items-start"], [1, "text-center", "sm:mt-0", "sm:ml-4", "sm:text-left"], ["id", "modal-headline", 1, "text-lg", "mt-2", "leading-6", "font-medium", "text-gray-700", "align-middle"], [1, "fas", "fa-toolbox"], [1, "ml-2"], [1, "ml-2", "font-semibold"], [1, "mt-2"], [1, "h-auto", "w-full", 3, "formGroup", "ngSubmit"], [1, "flex", "flex-row", "mt-5"], [1, "flex", "flex-col"], [1, "flex", "flex-row", "w-full", "mb-2"], [1, "flex", "flex-row", "w-1/2", "justify-start"], [1, "mr-5", "w-48"], ["for", "signal-distance", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], [1, "text-red-500"], ["id", "name", "type", "text", "formControlName", "name", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], [1, "mt-8", "text-sm"], [1, "flex", "flex-row", "w-full", "my-2"], [1, "flex", "flex-row", "w-1/2"], ["for", "numberOfReferenceCycles", 1, "block", "text-sm", "font-bold", "mb-2", "w-64", 3, "ngClass"], ["id", "numberOfReferenceCycles", "type", "number", "formControlName", "numberOfReferenceCycles", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], [1, "mt-8", "justify-end", "text-sm"], [1, "flex", "flex-row", "w-full"], [1, "mr-5", "w-64"], ["for", "stop-reaction", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "stop-reaction", 3, "text", "items", "selected", "select"], [1, "mt-8", "text-sm", "w-48"], [1, "ml-1", "inline-flex", "items-center", "text-md", "font-semibold", 3, "ngClass"], [1, "ml-2", "w-56", "text-sm", "font-normal"], [1, "flex", "flex-row", "w-1/2", "justify-start", "mt-5"], ["for", "toolStatus", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["readonly", "", "id", "toolStatus", "type", "text", "min", "0", "formControlName", "toolStatus", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-500", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "cursor-not-allowed", "text-xs"], ["for", "toolNumber", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "toolNumber", "type", "number", "min", "0", "formControlName", "toolNumber", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], ["formGroupName", "tolerance"], ["for", "tolerance-type", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "tolerance-type", 3, "text", "items", "selected", "select"], ["for", "tolerance-speed", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "tolerance-speed", "type", "number", "min", "0", "formControlName", "speedTolerance", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], ["for", "tolerance-torque", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "tolerance-torque", "type", "number", "min", "0", "formControlName", "torqueTolerance", 1, "appearance-none", "block", "w-48", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], [1, "bg-gray-100", "px-4", "py-3", "sm:px-6", "sm:flex", "sm:flex-row-reverse"], [1, "flex", "w-full", "rounded-md", "shadow-sm", "sm:ml-3", "sm:w-auto"], ["type", "submit", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-transparent", "px-4", "py-2", "text-base", "leading-6", "font-medium", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", 3, "ngClass", "click"], [1, "mt-3", "flex", "w-full", "rounded-md", "shadow-sm", "sm:mt-0", "sm:w-auto"], ["type", "button", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-gray-300", "px-4", "py-2", "bg-white", "text-base", "leading-6", "font-medium", "text-gray-700", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", "hover:text-gray-700", "hover:font-semibold", 3, "click"]], template: function ToolFormComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "h3", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](4, "i", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "span", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "span", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "div", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "form", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngSubmit", function ToolFormComponent_Template_form_ngSubmit_10_listener() { return ctx.submit(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](11, "div", 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "div", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](13, "div", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](14, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](15, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](16, "label", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](17, "Name");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](18, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](19, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](20, "input", 16);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](21, "div", 17);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](22, "div", 18);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](23, "div", 19);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](24, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](25, "label", 20);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](26, "Number of reference cycles");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](27, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](28, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](29, "input", 21);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](30, "div", 22);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](31, "div", 23);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](32, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](33, "div", 24);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](34, "label", 25);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](35, "Stop reaction");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](36, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](37, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](38, "app-dropdown", 26);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("select", function ToolFormComponent_Template_app_dropdown_select_38_listener($event) { return ctx.selected($event, "Reaction"); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](39, "div", 27);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](40, "span", 28);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](41);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](42, "span", 29);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](43, "Reference curve is calculated ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](44, "div", 23);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](45, "div", 30);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](46, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](47, "label", 31);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](48, "Tool status");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](49, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](50, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](51, "input", 32);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](52, "div");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](53, "label", 33);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](54, "Tool number");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](55, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](56, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](57, "input", 34);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](58, "div", 35);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](59, "div", 23);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](60, "div", 30);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](61, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](62, "label", 36);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](63, "Tolerance type");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](64, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](65, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](66, "app-dropdown", 37);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("select", function ToolFormComponent_Template_app_dropdown_select_66_listener($event) { return ctx.selected($event, "Tolerance"); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](67, "div", 23);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](68, "div", 30);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](69, "div", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](70, "label", 38);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](71, "Tolerance Speed");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](72, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](73, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](74, "input", 39);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](75, "div");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](76, "label", 40);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](77, "Tolerance Torque");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](78, "span", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](79, "*");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](80, "input", 41);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](81, "div", 42);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](82, "span", 43);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](83, "button", 44);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ToolFormComponent_Template_button_click_83_listener() { return ctx.submit(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](84);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](85, "span", 45);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](86, "button", 46);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ToolFormComponent_Template_button_click_86_listener() { return ctx.onCancel(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](87, " Cancel ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.edit ? "Edit Tool of" : "Create new tool for");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.plc.name);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("formGroup", ctx.toolForm);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("name") ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("name") && "border-red-500");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("numberOfReferenceCycles") ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("numberOfReferenceCycles") && "border-red-500");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("stopReaction") ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleMap"]("form");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", "Stop reaction")("items", ctx.reactions)("selected", ctx.selectedReaction);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](32, _c0, ctx.toolForm.get("referenceCurveIsCalculated").value === true, ctx.toolForm.get("referenceCurveIsCalculated").value === false));
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.toolForm.get("referenceCurveIsCalculated").value ? "\u2713" : "\u2715", "");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("toolStatus") ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("toolNumber") ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("toolNumber") && "border-red-500");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵattribute"]("disabled", ctx.toolForm.get("toolStatus").value === ctx.toolTypes[1] ? true : null);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("type", true) ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleMap"]("form");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", "Tolerance type")("items", ctx.toleranceTypes)("selected", ctx.selectedTolerance);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("speedTolerance", true) ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("speedTolerance", true) && "border-red-500");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("torqueTolerance", true) ? "text-red-500" : "text-gray-700");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("torqueTolerance", true) && "border-red-500");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.toolForm.invalid ? "cursor-not-allowed bg-gray-200 text-gray-400" : "text-white cursor-pointer btn hover:text-black");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵattribute"]("disabled", ctx.toolForm.invalid ? true : null);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.edit ? "Update" : "Create", " ");
    } }, directives: [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormGroupDirective"], _angular_common__WEBPACK_IMPORTED_MODULE_5__["NgClass"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["DefaultValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormControlName"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NumberValueAccessor"], _shared_components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_6__["DropdownComponent"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormGroupName"]], styles: [".btn[_ngcontent-%COMP%]{background-color: #2d3748; outline: none}", ".btn[_ngcontent-%COMP%]:hover{background-color: #e2e8f0}", "input[type=\"checkbox\"][_ngcontent-%COMP%]{box-shadow: none !important; outline: none !important;}"], changeDetection: 0 });
(function () { (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ToolFormComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                changeDetection: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ChangeDetectionStrategy"].OnPush,
                selector: 'tool-form',
                templateUrl: './tool-form.component.html',
                styles: [
                    '.btn{background-color: #2d3748; outline: none}',
                    '.btn:hover{background-color: #e2e8f0}',
                    'input[type="checkbox"]{box-shadow: none !important; outline: none !important;}',
                ],
            }]
    }], function () { return [{ type: _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"] }]; }, { plc: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
        }], edit: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
        }], data: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
        }], action: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
        }], cancel: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
        }] }); })();


/***/ }),

/***/ "TYcU":
/*!************************************************************!*\
  !*** ./src/app/modules/tools/container/tools.component.ts ***!
  \************************************************************/
/*! exports provided: ToolsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToolsComponent", function() { return ToolsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/store/actions */ "v8Ou");
/* harmony import */ var src_app_shared_modules_modal_container_modal_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/shared/modules/modal/container/modal.component */ "VRmN");
/* harmony import */ var src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/shared/enums/tool-status */ "/Qx1");
/* harmony import */ var _sharedEnums__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @sharedEnums */ "k5Aa");
/* harmony import */ var src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! src/app/shared/websocket/websocket.service */ "to+R");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _shared_components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../../shared/components/plc-list/plc-list.component */ "lOC7");
/* harmony import */ var _shared_components_table_table_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../../../shared/components/table/table.component */ "Xv+k");
/* harmony import */ var _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../components/tool-form/tool-form.component */ "7K1o");
/* harmony import */ var _components_calculation_calculation_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ../components/calculation/calculation.component */ "shSU");
/* harmony import */ var _shared_modules_modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ../../../shared/modules/modal/components/delete/delete.component */ "zfld");
/* harmony import */ var _shared_components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ../../../shared/components/no-data/no-data.component */ "sIB3");


















const _c0 = ["deleteModal"];
const _c1 = ["toolModal"];
const _c2 = ["toolEditModal"];
const _c3 = ["calculationModal"];
function ToolsComponent_div_1_Template(rf, ctx) { if (rf & 1) {
    const _r17 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "app-plc-list", 13);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("selected", function ToolsComponent_div_1_Template_app_plc_list_selected_1_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r17); const ctx_r16 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r16.selectedPLC($event); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("list", ctx_r0.plcs);
} }
function ToolsComponent_div_3_Template(rf, ctx) { if (rf & 1) {
    const _r19 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 14);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "span", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2, " Tools of ");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "span", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "button", 17);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ToolsComponent_div_3_Template_button_click_5_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r19); _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](6); return _r3.open(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](6, " New ");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r1.plc.name);
} }
function ToolsComponent_app_table_4_Template(rf, ctx) { if (rf & 1) {
    const _r21 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-table", 18);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("action", function ToolsComponent_app_table_4_Template_app_table_action_0_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r21); const ctx_r20 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r20.actionTableHandler($event); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("columns", ctx_r2.columns)("data", ctx_r2.table);
} }
function ToolsComponent_ng_template_7_Template(rf, ctx) { if (rf & 1) {
    const _r23 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "tool-form", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function ToolsComponent_ng_template_7_Template_tool_form_cancel_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r23); _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](6); return _r3.close(); })("action", function ToolsComponent_ng_template_7_Template_tool_form_action_0_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r23); const ctx_r24 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](6); ctx_r24.actionModalHandler($event); return _r3.close(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("plc", ctx_r5.plc)("edit", false);
} }
function ToolsComponent_ng_template_11_Template(rf, ctx) { if (rf & 1) {
    const _r26 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-calculation", 20);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function ToolsComponent_ng_template_11_Template_app_calculation_cancel_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r26); const ctx_r25 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](10); _r6.close(); ctx_r25.cancelCalculation(); return ctx_r25.progressStatus = 0; })("done", function ToolsComponent_ng_template_11_Template_app_calculation_done_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r26); const ctx_r27 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](10); _r6.close(); return ctx_r27.progressStatus = 0; });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("percentage", ctx_r8.progressStatus);
} }
function ToolsComponent_ng_template_15_Template(rf, ctx) { if (rf & 1) {
    const _r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "tool-form", 21);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function ToolsComponent_ng_template_15_Template_tool_form_cancel_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r29); _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](14); return _r9.close(); })("action", function ToolsComponent_ng_template_15_Template_tool_form_action_0_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r29); const ctx_r30 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](14); ctx_r30.actionModalHandler($event); return _r9.close(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("plc", ctx_r11.plc)("data", ctx_r11.tool)("edit", true);
} }
function ToolsComponent_ng_template_19_Template(rf, ctx) { if (rf & 1) {
    const _r32 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-delete-confirmation", 22);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function ToolsComponent_ng_template_19_Template_app_delete_confirmation_cancel_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r32); _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r12 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](18); return _r12.close(); })("action", function ToolsComponent_ng_template_19_Template_app_delete_confirmation_action_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r32); const ctx_r33 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); const _r12 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](18); ctx_r33.deleteTool(); return _r12.close(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("title", "Remove plc")("text", ctx_r14.removeText)("actionName", "Remove");
} }
function ToolsComponent_app_no_data_21_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-no-data", 23);
} if (rf & 2) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("title", "PLC(s) not found!")("text", "Please create at least one PLC")("image", "empty");
} }
class ToolsComponent {
    constructor(store, ws) {
        this.store = store;
        this.ws = ws;
        this.progressStatus = 0;
        this.wsEnums = _sharedEnums__WEBPACK_IMPORTED_MODULE_5__["WebsocketEnums"];
        this.columns = [
            {
                name: 'Name',
                sort_by: true,
                descending: true,
                property_name: 'name',
                cell_type: 'text',
            },
            {
                name: 'No. of ref cycles',
                sort_by: false,
                descending: true,
                property_name: 'numberOfReferenceCycles',
                cell_type: 'text',
            },
            {
                name: 'Tool Status',
                sort_by: false,
                descending: true,
                property_name: 'toolStatus',
                cell_type: 'text',
            },
            {
                name: 'Stop reaction',
                sort_by: false,
                descending: true,
                property_name: 'stopReaction',
                cell_type: 'text',
            },
            {
                name: 'Tolerance',
                sort_by: false,
                descending: true,
                property_name: 'tolerance',
                nested_property: 'type',
                cell_type: 'nested-text',
            },
            {
                name: 'Speed',
                sort_by: false,
                descending: true,
                property_name: 'tolerance',
                nested_property: 'speedTolerance',
                cell_type: 'nested-text',
            },
            {
                name: 'Torque',
                sort_by: false,
                descending: true,
                property_name: 'tolerance',
                nested_property: 'torqueTolerance',
                cell_type: 'nested-text',
            },
            {
                name: 'Reference curve calculated',
                sort_by: false,
                descending: true,
                property_name: 'referenceCurveIsCalculated',
                cell_type: 'check-mark',
            },
            {
                name: 'Automatic start of monitoring',
                sort_by: false,
                descending: true,
                property_name: 'automaticMonitoring',
                cell_type: 'check-mark',
            },
            {
                name: 'Actions',
                sort_by: false,
                cell_type: 'action',
                actions: [
                    {
                        type: 'start',
                        icon: 'fa-play',
                        text: '',
                        title: 'Start monitoring',
                    },
                    {
                        type: 'reference',
                        icon: 'fa-object-ungroup',
                        text: '',
                        title: 'Calculate reference curve',
                    },
                    {
                        type: 'edit',
                        icon: 'fa-edit',
                        text: '',
                        title: 'Edit',
                    },
                    {
                        type: 'delete',
                        icon: 'fa-trash-alt',
                        text: '',
                        title: 'Delete',
                    },
                ],
            },
        ];
        this.store$ = this.store;
    }
    ngOnInit() {
        this.ws.sub(this.wsEnums.ProgressBar, true).subscribe((message) => {
            const msg = message && JSON.parse(message);
            msg &&
                (this.progressStatus =
                    Math.round((100 / msg.calculationStatus.total) * msg.calculationStatus.current * 100) /
                        100);
        });
        this.storeSubscription = this.store$.subscribe((response) => {
            response.plcs.plcs.length ? (this.plcs = [...response.plcs.plcs]) : (this.plcs = []);
            response.tools.tools.length
                ? ((this.tools = [...response.tools.tools]),
                    response.tools.used && this.usedTool(response.tools.used),
                    this.userFriendlyEnums(),
                    this.tools.sort((a, b) => a.name.localeCompare(b.name)))
                : (this.tools = []);
            this.plc && this.selectedPLC(this.plc);
        });
    }
    deleteTool() {
        this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["DeleteTool"](this.tool));
    }
    ngOnDestroy() {
        this.storeSubscription && this.storeSubscription.unsubscribe();
        this.ws.unsub();
    }
    selectedPLC(plc) {
        this.plc = Object.assign({}, plc);
        this.table = [...this.tools.filter((x) => x.plcId === plc.id)];
    }
    actionTableHandler(event) {
        this.tool = Object.assign({}, event.item);
        switch (event.type) {
            case 'edit': {
                this.toolEditModal.open();
                break;
            }
            case 'delete': {
                this.removeText = `Do you want to remove ${event.item.name} of ${this.plc.name} ?`;
                this.deleteModal.open();
                break;
            }
            case 'reference': {
                const copy = Object.assign({}, this.uppercaseEnums(event.item));
                copy.calculateReferenceCurve = true;
                this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["UpdateTool"](copy));
                this.calculationModal.open();
                break;
            }
            case 'start': {
                const copy = Object.assign({}, this.uppercaseEnums(event.item));
                copy.automaticMonitoring = !copy.automaticMonitoring;
                this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["UpdateTool"](copy));
                break;
            }
            default: {
                break;
            }
        }
    }
    actionModalHandler(item) {
        item.data = Object.assign({}, this.uppercaseEnums(item.data));
        switch (item.edit) {
            case true: {
                this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["UpdateTool"](item.data));
                break;
            }
            case false: {
                this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["CreateTool"](item.data));
                break;
            }
        }
    }
    cancelCalculation() {
        const copy = Object.assign({}, this.uppercaseEnums(this.tool));
        copy.calculateReferenceCurve = false;
        this.store.dispatch(new src_app_store_actions__WEBPACK_IMPORTED_MODULE_2__["UpdateTool"](copy));
    }
    usedTool(data) {
        this.tools = [
            ...this.tools.map((x) => {
                if (x.plcId === (data === null || data === void 0 ? void 0 : data.id) && x.toolNumber === (data === null || data === void 0 ? void 0 : data.toolNumber)) {
                    return Object.assign(Object.assign({}, x), { isActive: true });
                }
                else {
                    return Object.assign(Object.assign({}, x), { isActive: false });
                }
            }),
        ];
    }
    // use user readable values of enums
    userFriendlyEnums() {
        for (let i = 0; i < this.tools.length; i++) {
            const copy = Object.assign({}, this.tools[i]);
            const tolerance = (copy === null || copy === void 0 ? void 0 : copy.tolerance) && Object.assign({}, copy === null || copy === void 0 ? void 0 : copy.tolerance);
            copy.stopReaction &&
                (copy.stopReaction = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_5__["StopReactionType"]).find((x) => x[0] === this.tools[i].stopReaction)[1]);
            copy.toolStatus = Object.entries(src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__["ToolStatusType"]).find((x) => x[0] === this.tools[i].toolStatus)[1];
            (copy === null || copy === void 0 ? void 0 : copy.tolerance) && (tolerance === null || tolerance === void 0 ? void 0 : tolerance.type) &&
                ((tolerance.type = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_5__["ToleranceEnum"]).find((x) => { var _a; return x[0] === ((_a = copy === null || copy === void 0 ? void 0 : copy.tolerance) === null || _a === void 0 ? void 0 : _a.type); })[1]),
                    (copy.tolerance = Object.assign({}, tolerance)));
            this.tools[i] = Object.assign({}, copy);
        }
    }
    // change enums to BE format
    uppercaseEnums(object) {
        const copy = Object.assign({}, object);
        copy.stopReaction = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_5__["StopReactionType"]).find((x) => x[1] === copy.stopReaction)[0];
        copy.toolStatus = Object.entries(src_app_shared_enums_tool_status__WEBPACK_IMPORTED_MODULE_4__["ToolStatusType"]).find((x) => x[1] === copy.toolStatus)[0];
        const tolerance = Object.assign({}, copy.tolerance);
        tolerance.type = Object.entries(_sharedEnums__WEBPACK_IMPORTED_MODULE_5__["ToleranceEnum"]).find((x) => x[1] === tolerance.type)[0];
        copy.tolerance = Object.assign({}, tolerance);
        return copy;
    }
}
ToolsComponent.ɵfac = function ToolsComponent_Factory(t) { return new (t || ToolsComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_6__["WebsocketService"])); };
ToolsComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: ToolsComponent, selectors: [["app-tools"]], viewQuery: function ToolsComponent_Query(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c0, 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c1, 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c2, 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c3, 1);
    } if (rf & 2) {
        let _t;
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.deleteModal = _t.first);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.toolModal = _t.first);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.toolEditModal = _t.first);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.calculationModal = _t.first);
    } }, decls: 22, vars: 7, consts: [[1, "flex", "flex-row", "h-full", "w-full", "custom-scroll"], ["class", "h-full", 4, "ngIf"], [1, "w-full", "custom-scroll", "overflow-x-auto", "flex-grow"], ["class", "flex flex-row justify-between font-thin border border-b-2 border-gray-200 w-full", 4, "ngIf"], [3, "columns", "data", "action", 4, "ngIf"], [3, "closeOnOutsideClick"], ["toolModal", ""], ["modalBody", ""], ["calculationModal", ""], ["toolEditModal", ""], ["deleteModal", ""], [3, "title", "text", "image", 4, "ngIf"], [1, "h-full"], [3, "list", "selected"], [1, "flex", "flex-row", "justify-between", "font-thin", "border", "border-b-2", "border-gray-200", "w-full"], [1, "ml-2", "mt-4", "text-2xl"], [1, "font-normal", "px-2"], [1, "bg-white", "mx-2", "my-2", "hover:text-black", "hover:bg-gray-100", "h-auto", "rounded", "text-white", "font-thin", "py-2", "px-4", "border", "border-gray-400", "shadow", "btn", 3, "click"], [3, "columns", "data", "action"], [3, "plc", "edit", "cancel", "action"], [3, "percentage", "cancel", "done"], [3, "plc", "data", "edit", "cancel", "action"], [3, "title", "text", "actionName", "cancel", "action"], [3, "title", "text", "image"]], template: function ToolsComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, ToolsComponent_div_1_Template, 2, 1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, ToolsComponent_div_3_Template, 7, 1, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](4, ToolsComponent_app_table_4_Template, 1, 2, "app-table", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "app-modal", 5, 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, ToolsComponent_ng_template_7_Template, 1, 2, "ng-template", null, 7, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "app-modal", 5, 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](11, ToolsComponent_ng_template_11_Template, 1, 1, "ng-template", null, 7, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](13, "app-modal", 5, 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](15, ToolsComponent_ng_template_15_Template, 1, 3, "ng-template", null, 7, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](17, "app-modal", null, 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](19, ToolsComponent_ng_template_19_Template, 1, 3, "ng-template", null, 7, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](21, ToolsComponent_app_no_data_21_Template, 1, 3, "app-no-data", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.plcs.length);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.plc);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.plcs.length > 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.plcs.length);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_7__["NgIf"], src_app_shared_modules_modal_container_modal_component__WEBPACK_IMPORTED_MODULE_3__["ModalComponent"], _shared_components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_8__["PlcListComponent"], _shared_components_table_table_component__WEBPACK_IMPORTED_MODULE_9__["TableComponent"], _components_tool_form_tool_form_component__WEBPACK_IMPORTED_MODULE_10__["ToolFormComponent"], _components_calculation_calculation_component__WEBPACK_IMPORTED_MODULE_11__["CalculationComponent"], _shared_modules_modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_12__["DeleteComponent"], _shared_components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_13__["NoDataComponent"]], encapsulation: 2 });
(function () { (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ToolsComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-tools',
                templateUrl: './tools.component.html',
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"] }, { type: src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_6__["WebsocketService"] }]; }, { deleteModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['deleteModal']
        }], toolModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['toolModal']
        }], toolEditModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['toolEditModal']
        }], calculationModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['calculationModal']
        }] }); })();


/***/ }),

/***/ "shSU":
/*!*******************************************************************************!*\
  !*** ./src/app/modules/tools/components/calculation/calculation.component.ts ***!
  \*******************************************************************************/
/*! exports provided: CalculationComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CalculationComponent", function() { return CalculationComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "ofXK");



function CalculationComponent_button_9_Template(rf, ctx) { if (rf & 1) {
    const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 9);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CalculationComponent_button_9_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r3); const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r2.cancel.emit(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, " Abort calculation ");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function CalculationComponent_button_10_Template(rf, ctx) { if (rf & 1) {
    const _r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CalculationComponent_button_10_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r5); const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r4.cancel.emit(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, " Acknowledged ");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
class CalculationComponent {
    constructor() {
        this.cancel = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.done = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.progress = 0;
    }
    set percentage(per) {
        this.progress = per;
    }
}
CalculationComponent.ɵfac = function CalculationComponent_Factory(t) { return new (t || CalculationComponent)(); };
CalculationComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CalculationComponent, selectors: [["app-calculation"]], inputs: { percentage: "percentage" }, outputs: { cancel: "cancel", done: "done" }, decls: 11, vars: 6, consts: [[1, "holder"], [1, "mt-4"], [1, "font-thin", "mx-5", "mt-2"], [1, "mx-5", "h-6", "bg-gray-200", "rounded-md"], [1, "mt-1", "bg-gray-800", "h-6", "rounded-md", "text-center", "transition-all", "duration-500"], [1, "text-white", "text-sm", "font-thin", 3, "ngClass"], [1, "mx-5", "mt-2", "flex", "flex-row", "justify-end"], ["class", "inline-flex justify-center rounded-md border  border-gray-300 px-4 py-2 bg-gray-100 text-base leading-6 font-medium text-gray-700 shadow-sm focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5 hover:bg-red-500 hover:text-white hover:font-semibold", 3, "click", 4, "ngIf"], ["class", "inline-flex justify-center rounded-md border  border-gray-300 px-4 py-2 bg-gray-100 text-base leading-6 font-medium text-gray-700 shadow-sm focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5 hover:bg-green-500 hover:text-white hover:font-semibold", 3, "click", 4, "ngIf"], [1, "inline-flex", "justify-center", "rounded-md", "border", "border-gray-300", "px-4", "py-2", "bg-gray-100", "text-base", "leading-6", "font-medium", "text-gray-700", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", "hover:bg-red-500", "hover:text-white", "hover:font-semibold", 3, "click"], [1, "inline-flex", "justify-center", "rounded-md", "border", "border-gray-300", "px-4", "py-2", "bg-gray-100", "text-base", "leading-6", "font-medium", "text-gray-700", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", "hover:bg-green-500", "hover:text-white", "hover:font-semibold", 3, "click"]], template: function CalculationComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "span", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3, "Calculating reference curve");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "span", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](9, CalculationComponent_button_9_Template, 2, 0, "button", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](10, CalculationComponent_button_10_Template, 2, 0, "button", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleProp"]("width", ctx.progress + "%");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.progress === 0 ? "text-gray-800" : "text-white");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.progress + "%");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.progress < 100);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.progress === 100);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgIf"]], styles: [".holder[_ngcontent-%COMP%]{width: 400px; height: 120px}"] });
(function () { (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](CalculationComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-calculation',
                template: `
    <div class="holder">
      <div class="mt-4">
        <span class="font-thin mx-5 mt-2 ">Calculating reference curve</span>
        <div class=" mx-5  h-6 bg-gray-200 rounded-md">
          <div
            class="mt-1 bg-gray-800 h-6 rounded-md text-center transition-all duration-500"
            [style.width]="progress + '%'"
          >
            <span
              [ngClass]="progress === 0 ? 'text-gray-800' : 'text-white'"
              class="text-white text-sm font-thin "
              >{{ progress + '%' }}</span
            >
          </div>
        </div>
      </div>

      <div class="mx-5 mt-2 flex flex-row justify-end">
        <button
          *ngIf="progress < 100"
          (click)="cancel.emit()"
          class="inline-flex justify-center rounded-md border  border-gray-300 px-4 py-2 bg-gray-100 text-base leading-6 font-medium text-gray-700 shadow-sm focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5 hover:bg-red-500 hover:text-white hover:font-semibold"
        >
          Abort calculation
        </button>
        <button
          *ngIf="progress === 100"
          (click)="cancel.emit()"
          class="inline-flex justify-center rounded-md border  border-gray-300 px-4 py-2 bg-gray-100 text-base leading-6 font-medium text-gray-700 shadow-sm focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5 hover:bg-green-500 hover:text-white hover:font-semibold"
        >
          Acknowledged
        </button>
      </div>
    </div>
  `,
                styles: ['.holder{width: 400px; height: 120px}'],
            }]
    }], null, { percentage: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
        }], cancel: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
        }], done: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
        }] }); })();


/***/ })

}]);
//# sourceMappingURL=modules-tools-tools-module-es2015.js.map