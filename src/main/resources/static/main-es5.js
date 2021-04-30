(function () {
  function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread(); }

  function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); }

  function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

  function _iterableToArray(iter) { if (typeof Symbol !== "undefined" && Symbol.iterator in Object(iter)) return Array.from(iter); }

  function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) return _arrayLikeToArray(arr); }

  function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

  function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

  function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

  function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

  (window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"], {
    /***/
    "+tET":
    /*!***********************************************!*\
      !*** ./src/app/shared/enums/stop-reaction.ts ***!
      \***********************************************/

    /*! exports provided: StopReactionType */

    /***/
    function tET(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "StopReactionType", function () {
        return StopReactionType;
      });

      var StopReactionType;

      (function (StopReactionType) {
        StopReactionType["IMMEDIATE"] = "Immediate";
        StopReactionType["TOP_POSITION"] = "Top position";
        StopReactionType["DO_NOTHING"] = "Do nothing";
      })(StopReactionType || (StopReactionType = {}));
      /***/

    },

    /***/
    "//oq":
    /*!*********************************************!*\
      !*** ./src/app/store/actions/plc.action.ts ***!
      \*********************************************/

    /*! exports provided: LOAD_PLCS, LOAD_PLCS_SUCCESS, LOAD_PLCS_FAIL, LoadPlcs, LoadPlcsSuccess, LoadPlcsFail, CREATE_PLC, CREATE_PLC_SUCCESS, CREATE_PLC_FAIL, CreatePlc, CreatePlcSuccess, CreatePlcFail, REMOVE_PLC, REMOVE_PLC_SUCCESS, REMOVE_PLC_FAIL, RemovePlc, RemovePlcSuccess, RemovePlcFail, UPDATE_PLC, UPDATE_PLC_SUCCESS, UPDATE_PLC_FAIL, UpdatePlc, UpdatePlcSuccess, UpdatePlcFail */

    /***/
    function oq(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS", function () {
        return LOAD_PLCS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS_SUCCESS", function () {
        return LOAD_PLCS_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS_FAIL", function () {
        return LOAD_PLCS_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcs", function () {
        return LoadPlcs;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcsSuccess", function () {
        return LoadPlcsSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcsFail", function () {
        return LoadPlcsFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC", function () {
        return CREATE_PLC;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC_SUCCESS", function () {
        return CREATE_PLC_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC_FAIL", function () {
        return CREATE_PLC_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreatePlc", function () {
        return CreatePlc;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreatePlcSuccess", function () {
        return CreatePlcSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreatePlcFail", function () {
        return CreatePlcFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC", function () {
        return REMOVE_PLC;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC_SUCCESS", function () {
        return REMOVE_PLC_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC_FAIL", function () {
        return REMOVE_PLC_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "RemovePlc", function () {
        return RemovePlc;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "RemovePlcSuccess", function () {
        return RemovePlcSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "RemovePlcFail", function () {
        return RemovePlcFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC", function () {
        return UPDATE_PLC;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC_SUCCESS", function () {
        return UPDATE_PLC_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC_FAIL", function () {
        return UPDATE_PLC_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlc", function () {
        return UpdatePlc;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlcSuccess", function () {
        return UpdatePlcSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlcFail", function () {
        return UpdatePlcFail;
      });

      var LOAD_PLCS = '[PLC] Load all plcs';
      var LOAD_PLCS_SUCCESS = '[PLC] Load all plcs success';
      var LOAD_PLCS_FAIL = '[PLC] Load all plcs failed';

      var LoadPlcs = function LoadPlcs() {
        _classCallCheck(this, LoadPlcs);

        this.type = LOAD_PLCS;
      };

      var LoadPlcsSuccess = function LoadPlcsSuccess(payload) {
        _classCallCheck(this, LoadPlcsSuccess);

        this.payload = payload;
        this.type = LOAD_PLCS_SUCCESS;
      };

      var LoadPlcsFail = function LoadPlcsFail(payload) {
        _classCallCheck(this, LoadPlcsFail);

        this.payload = payload;
        this.type = LOAD_PLCS_FAIL;
      };

      var CREATE_PLC = '[PLC] Create plc';
      var CREATE_PLC_SUCCESS = '[PLC] Create plc success';
      var CREATE_PLC_FAIL = '[PLC] Create plc fail';

      var CreatePlc = function CreatePlc(payload) {
        _classCallCheck(this, CreatePlc);

        this.payload = payload;
        this.type = CREATE_PLC;
      };

      var CreatePlcSuccess = function CreatePlcSuccess(payload) {
        _classCallCheck(this, CreatePlcSuccess);

        this.payload = payload;
        this.type = CREATE_PLC_SUCCESS;
      };

      var CreatePlcFail = function CreatePlcFail(payload) {
        _classCallCheck(this, CreatePlcFail);

        this.payload = payload;
        this.type = CREATE_PLC_FAIL;
      };

      var REMOVE_PLC = '[PLC] Remove plc';
      var REMOVE_PLC_SUCCESS = '[PLC] Remove plc success';
      var REMOVE_PLC_FAIL = '[PLC] Remove plc fail';

      var RemovePlc = function RemovePlc(payload) {
        _classCallCheck(this, RemovePlc);

        this.payload = payload;
        this.type = REMOVE_PLC;
      };

      var RemovePlcSuccess = function RemovePlcSuccess(payload) {
        _classCallCheck(this, RemovePlcSuccess);

        this.payload = payload;
        this.type = REMOVE_PLC_SUCCESS;
      };

      var RemovePlcFail = function RemovePlcFail(payload) {
        _classCallCheck(this, RemovePlcFail);

        this.payload = payload;
        this.type = REMOVE_PLC_FAIL;
      };

      var UPDATE_PLC = '[PLC] Update plc';
      var UPDATE_PLC_SUCCESS = '[PLC] Update plc success';
      var UPDATE_PLC_FAIL = '[PLC] Update plc fail';

      var UpdatePlc = function UpdatePlc(payload) {
        _classCallCheck(this, UpdatePlc);

        this.payload = payload;
        this.type = UPDATE_PLC;
      };

      var UpdatePlcSuccess = function UpdatePlcSuccess(payload) {
        _classCallCheck(this, UpdatePlcSuccess);

        this.payload = payload;
        this.type = UPDATE_PLC_SUCCESS;
      };

      var UpdatePlcFail = function UpdatePlcFail(payload) {
        _classCallCheck(this, UpdatePlcFail);

        this.payload = payload;
        this.type = UPDATE_PLC_FAIL;
      };
      /***/

    },

    /***/
    "/3YG":
    /*!**************************************************************!*\
      !*** ./src/app/layout/components/header/header.component.ts ***!
      \**************************************************************/

    /*! exports provided: HeaderComponent */

    /***/
    function YG(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "HeaderComponent", function () {
        return HeaderComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var HeaderComponent = function HeaderComponent() {
        _classCallCheck(this, HeaderComponent);
      };

      HeaderComponent.ɵfac = function HeaderComponent_Factory(t) {
        return new (t || HeaderComponent)();
      };

      HeaderComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: HeaderComponent,
        selectors: [["app-header"]],
        decls: 8,
        vars: 0,
        consts: [[1, "siemens-navbar"], [1, "bg-gray-800", "text-white", "px-8"], [1, "flex", "justify-between", "h-12", "m-auto"], [1, "flex", "flex-col", "justify-center", "h-full"], ["src", "assets/img/logo.svg", "alt", "Siemens", 1, "w-32"], [1, "font-thin", "text-xl"]],
        template: function HeaderComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "header", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](4, "img", 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "span", 5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](7, "Metal Forming");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }
        },
        styles: [".siemens-navbar[_ngcontent-%COMP%]::before {\n  content: \" \";\n  display: block;\n  height: 4px;\n  right: 0;\n  top: -4px;\n  background-image: linear-gradient(90deg, #50bebe 0, #099 33%, #0099b0 66%, #0099cb);\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L2NvbXBvbmVudHMvaGVhZGVyL2hlYWRlci5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFDRTtFQUNFLFlBQUE7RUFDQSxjQUFBO0VBQ0EsV0FBQTtFQUNBLFFBQUE7RUFDQSxTQUFBO0VBQ0EsbUZBQUE7QUFBSiIsImZpbGUiOiJzcmMvYXBwL2xheW91dC9jb21wb25lbnRzL2hlYWRlci9oZWFkZXIuY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuc2llbWVucy1uYXZiYXIge1xuICAmOjpiZWZvcmUge1xuICAgIGNvbnRlbnQ6ICcgJztcbiAgICBkaXNwbGF5OiBibG9jaztcbiAgICBoZWlnaHQ6IDRweDtcbiAgICByaWdodDogMDtcbiAgICB0b3A6IC00cHg7XG4gICAgYmFja2dyb3VuZC1pbWFnZTogbGluZWFyLWdyYWRpZW50KDkwZGVnLCAjNTBiZWJlIDAsICMwOTkgMzMlLCAjMDA5OWIwIDY2JSwgIzAwOTljYik7XG4gIH1cbn1cbiJdfQ== */"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](HeaderComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-header',
            template: "\n    <header class=\"siemens-navbar\">\n      <div class=\"bg-gray-800 text-white px-8\">\n        <div class=\"flex justify-between h-12 m-auto\">\n          <div class=\"flex flex-col justify-center h-full\">\n            <img class=\"w-32\" src=\"assets/img/logo.svg\" alt=\"Siemens\" />\n          </div>\n          <div class=\"flex flex-col justify-center h-full\">\n            <span class=\"font-thin text-xl\">Metal Forming</span>\n          </div>\n        </div>\n      </div>\n    </header>\n  ",
            styleUrls: ['./header.component.scss']
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    0:
    /*!***************************!*\
      !*** multi ./src/main.ts ***!
      \***************************/

    /*! no static exports found */

    /***/
    function _(module, exports, __webpack_require__) {
      module.exports = __webpack_require__(
      /*! /Users/jakub/SIEMENS/APPLICATIONS/MetalForming/FE/metal-forming-fe/src/main.ts */
      "zUnb");
      /***/
    },

    /***/
    "4yaq":
    /*!******************************************************************!*\
      !*** ./src/app/shared/components/dropdown/dropdown.component.ts ***!
      \******************************************************************/

    /*! exports provided: DropdownComponent */

    /***/
    function yaq(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DropdownComponent", function () {
        return DropdownComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _animations_animations__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ../../animations/animations */
      "fk77");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      var _c0 = function _c0(a0, a1, a2) {
        return {
          "bg-gray-200 hover:bg-gray-200 cursor-not-allowed": a0,
          "py-2 text-sm leading-5": a1,
          "py-3 text-xs h-8 leading-tight": a2
        };
      };

      function DropdownComponent_div_8_div_3_Template(rf, ctx) {
        if (rf & 1) {
          var _r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 11);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function DropdownComponent_div_8_div_3_Template_div_click_0_listener($event) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r4);

            var item_r2 = ctx.$implicit;

            var ctx_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r3.onSelect(item_r2, $event);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var item_r2 = ctx.$implicit;

          var ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction3"](2, _c0, item_r2 === ctx_r1.selected, ctx_r1.style === "separate", ctx_r1.style === "form"));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", item_r2, " ");
        }
      }

      function DropdownComponent_div_8_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnamespaceSVG"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnamespaceHTML"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 7);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 8);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 9);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, DropdownComponent_div_8_div_3_Template, 2, 6, "div", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("@toggle", ctx_r0.state)("ngClass", ctx_r0.width ? ctx_r0.width : "w-48");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.items);
        }
      }

      var _c1 = function _c1(a0, a1, a2, a3, a4) {
        return {
          "py-2 text-sm leading-5 text-gray-700": a0,
          "py-2 text-xs h-8 leading-tight": a1,
          "text-gray-500": a2,
          "text-gray-700": a3,
          "bg-gray-200": a4
        };
      };

      var _c2 = function _c2(a0) {
        return {
          "transform rotate-180": a0
        };
      };

      var DropdownComponent = /*#__PURE__*/function () {
        function DropdownComponent() {
          _classCallCheck(this, DropdownComponent);

          this.style = 'separate';
          this.select = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.state = 'closed';
          this.hideList = true;
        }

        _createClass(DropdownComponent, [{
          key: "close",
          value: function close() {
            if (this.state === 'opened') {
              this.hide();
            }
          }
        }, {
          key: "toggle",
          value: function toggle(event) {
            event.stopPropagation();

            if (this.state === 'closed') {
              this.show();
            } else {
              this.hide();
            }
          }
        }, {
          key: "onSelect",
          value: function onSelect(item, event) {
            event.stopPropagation();

            if (this.selected !== item && !this.hideList) {
              this.select.emit(item);
              this.hide();
            }
          }
        }, {
          key: "show",
          value: function show() {
            this.hideList = false;
            this.state = 'opened';
          }
        }, {
          key: "hide",
          value: function hide() {
            var _this = this;

            this.state = 'closed';
            setTimeout(function () {
              return _this.hideList = true;
            }, 100);
          }
        }]);

        return DropdownComponent;
      }();

      DropdownComponent.ɵfac = function DropdownComponent_Factory(t) {
        return new (t || DropdownComponent)();
      };

      DropdownComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: DropdownComponent,
        selectors: [["app-dropdown"]],
        hostBindings: function DropdownComponent_HostBindings(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function DropdownComponent_click_HostBindingHandler($event) {
              return ctx.close($event.target);
            }, false, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵresolveDocument"])("keydown.escape", function DropdownComponent_keydown_escape_HostBindingHandler($event) {
              return ctx.close($event.target);
            }, false, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵresolveDocument"]);
          }
        },
        inputs: {
          text: "text",
          items: "items",
          selected: "selected",
          width: "width",
          style: "style"
        },
        outputs: {
          select: "select"
        },
        decls: 9,
        vars: 13,
        consts: [[1, "relative", "inline-block", "text-left", 3, "ngClass"], [1, "rounded-md", "shadow-sm"], ["type", "button", 1, "inline-flex", "justify-between", "w-full", "rounded", "border", "border-gray-300", "px-4", "bg-white", "focus:outline-none", "active:bg-gray-50", "transition", "ease-in-out", "duration-150", 3, "ngClass", "click"], [1, "truncate"], ["viewBox", "0 0 20 20", "fill", "currentColor", 1, "-mr-1", "ml-2", "h-5", "w-5", 3, "ngClass"], ["fill-rule", "evenodd", "d", "M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z", "clip-rule", "evenodd"], ["class", "origin-top-left absolute z-10 left-0 mt-1 rounded-md shadow-lg opacity-0", 3, "ngClass", 4, "ngIf"], [1, "origin-top-left", "absolute", "z-10", "left-0", "mt-1", "rounded-md", "shadow-lg", "opacity-0", 3, "ngClass"], [1, "rounded-md", "bg-white", "shadow-xs"], [1, "py-1"], ["class", "px-4 text-xs leading-5 text-gray-700 hover:bg-gray-100 hover:text-gray-900 focus:outline-none focus:bg-gray-100 focus:text-gray-900 cursor-pointer", 3, "ngClass", "click", 4, "ngFor", "ngForOf"], [1, "px-4", "text-xs", "leading-5", "text-gray-700", "hover:bg-gray-100", "hover:text-gray-900", "focus:outline-none", "focus:bg-gray-100", "focus:text-gray-900", "cursor-pointer", 3, "ngClass", "click"]],
        template: function DropdownComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "span", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "button", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function DropdownComponent_Template_button_click_3_listener($event) {
              return ctx.toggle($event);
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "span", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnamespaceSVG"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "svg", 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](7, "path", 5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](8, DropdownComponent_div_8_Template, 4, 3, "div", 6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.width ? ctx.width : "w-48");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction5"](5, _c1, ctx.style === "separate", ctx.style === "form", ctx.style === "form" && !ctx.selected, ctx.style === "form" && ctx.selected, ctx.state === "closed" && ctx.style === "form"));

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.selected ? ctx.selected : ctx.text, " ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction1"](11, _c2, ctx.state === "opened"));

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.hideList);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"]],
        encapsulation: 2,
        data: {
          animation: [_animations_animations__WEBPACK_IMPORTED_MODULE_1__["toggle"]]
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](DropdownComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-dropdown',
            template: "\n    <div class=\"relative inline-block text-left \" [ngClass]=\"width ? width : 'w-48'\">\n      <div>\n        <span class=\"rounded-md shadow-sm\">\n          <button\n            type=\"button\"\n            class=\"inline-flex justify-between w-full rounded border border-gray-300 px-4 bg-white focus:outline-none active:bg-gray-50 transition ease-in-out duration-150\"\n            [ngClass]=\"{\n              'py-2 text-sm leading-5 text-gray-700': style === 'separate',\n              'py-2 text-xs h-8 leading-tight': style === 'form',\n              'text-gray-500': style === 'form' && !selected,\n              'text-gray-700': style === 'form' && selected,\n              'bg-gray-200': state === 'closed' && style === 'form'\n            }\"\n            (click)=\"toggle($event)\"\n          >\n            <span class=\"truncate\">\n              {{ selected ? selected : text }}\n            </span>\n            <svg\n              [ngClass]=\"{ 'transform rotate-180': state === 'opened' }\"\n              class=\"-mr-1 ml-2 h-5 w-5\"\n              viewBox=\"0 0 20 20\"\n              fill=\"currentColor\"\n            >\n              <path\n                fill-rule=\"evenodd\"\n                d=\"M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z\"\n                clip-rule=\"evenodd\"\n              />\n            </svg>\n          </button>\n        </span>\n      </div>\n\n      <div\n        *ngIf=\"!hideList\"\n        [@toggle]=\"state\"\n        class=\"origin-top-left absolute z-10 left-0 mt-1 rounded-md shadow-lg opacity-0\"\n        [ngClass]=\"width ? width : 'w-48'\"\n      >\n        <div class=\"rounded-md bg-white shadow-xs\">\n          <div class=\"py-1\">\n            <div\n              *ngFor=\"let item of items\"\n              class=\"px-4 text-xs leading-5 text-gray-700 hover:bg-gray-100 hover:text-gray-900 focus:outline-none focus:bg-gray-100 focus:text-gray-900 cursor-pointer\"\n              (click)=\"onSelect(item, $event)\"\n              [ngClass]=\"{\n                'bg-gray-200 hover:bg-gray-200 cursor-not-allowed': item === selected,\n                'py-2 text-sm leading-5': style === 'separate',\n                'py-3 text-xs h-8 leading-tight': style === 'form'\n              }\"\n            >\n              {{ item }}\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  ",
            animations: [_animations_animations__WEBPACK_IMPORTED_MODULE_1__["toggle"]]
          }]
        }], null, {
          text: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          items: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          selected: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          width: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          style: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          select: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }],
          close: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"],
            args: ['document:click', ['$event.target']]
          }, {
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"],
            args: ['document:keydown.escape', ['$event.target']]
          }]
        });
      })();
      /***/

    },

    /***/
    "AytR":
    /*!*****************************************!*\
      !*** ./src/environments/environment.ts ***!
      \*****************************************/

    /*! exports provided: environment */

    /***/
    function AytR(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "environment", function () {
        return environment;
      }); // This file can be replaced during build by using the `fileReplacements` array.
      // `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
      // The list of file replacements can be found in `angular.json`.


      var environment = {
        production: false,
        base_url: 'http://localhost:3000/api/',
        url_webscoket: 'ws://localhost:3000/api/ws'
      };
      /*
       * For easier debugging in development mode, you can import the following file
       * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
       *
       * This import should be commented out in production mode because it will have a negative impact
       * on performance if an error is thrown.
       */
      // import 'zone.js/dist/zone-error';  // Included with Angular CLI.

      /***/
    },

    /***/
    "CKoO":
    /*!************************************************!*\
      !*** ./src/app/store/reducers/tool.reducer.ts ***!
      \************************************************/

    /*! exports provided: ToolReducer */

    /***/
    function CKoO(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ToolReducer", function () {
        return ToolReducer;
      });
      /* harmony import */


      var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @actions */
      "v8Ou");

      var initialState = {
        tools: [],
        loading: false,
        loaded: true,
        error: null,
        used: null
      };

      function ToolReducer() {
        var toolState = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : initialState;
        var action = arguments.length > 1 ? arguments[1] : undefined;

        switch (action.type) {
          case _actions__WEBPACK_IMPORTED_MODULE_0__["ADD_NEW_TOOL"]:
            {
              var tools = [].concat(_toConsumableArray(toolState.tools), [action.payload]);
              return Object.assign(Object.assign({}, toolState), {
                tools: tools
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_TOOL"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                error: null
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_TOOL_SUCCESS"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                tools: [].concat(_toConsumableArray(toolState.tools), [action.payload]),
                error: null
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CURRENTLY_USED"]:
            {
              var _tools = _toConsumableArray(toolState.tools).map(function (x) {
                if (x.plcId === action.payload.id && x.toolNumber === action.payload.toolNumber) {
                  return Object.assign(Object.assign({}, x), {
                    isActive: true
                  });
                } else {
                  return Object.assign(Object.assign({}, x), {
                    isActive: false
                  });
                }
              });

              return Object.assign(Object.assign({}, toolState), {
                tools: _tools,
                used: action.payload
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                error: null,
                loading: true
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS_SUCCESS"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                tools: action.payload,
                loaded: true,
                loading: false
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_TOOL"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                error: null,
                loading: true
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS_PLC"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                loading: true
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS_PLC_SUCCESS"]:
            {
              var _tools2 = _toConsumableArray(toolState.tools);

              action.payload.forEach(function (tool) {
                return _tools2.findIndex(function (x) {
                  return x.id === tool.id;
                }) === -1 && _tools2.push(tool);
              });
              return Object.assign(Object.assign({}, toolState), {
                loaded: true,
                loading: false,
                tools: _tools2
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS_PLC_FAIL"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                loading: false,
                loaded: false,
                error: action.payload
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_TOOL_SUCCESS"]:
            {
              var oldData = toolState.tools.find(function (x) {
                return x.id === action.payload.id;
              });

              var _tools3 = _toConsumableArray(toolState.tools.map(function (x) {
                if (x.id === action.payload.id) {
                  return Object.assign(Object.assign({}, action.payload), {
                    isActive: oldData.isActive
                  });
                } else {
                  return x;
                }
              }));

              return Object.assign(Object.assign({}, toolState), {
                loaded: true,
                loading: false,
                tools: _tools3
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["DELETE_TOOL"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                error: null
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["DELETE_TOOL_SUCCESS"]:
            {
              var _tools4 = _toConsumableArray(toolState.tools.filter(function (x) {
                return x.id !== action.payload.id;
              }));

              return Object.assign(Object.assign({}, toolState), {
                tools: _tools4
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_TOOL_FAIL"]:
          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_TOOLS_FAIL"]:
          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_TOOL_FAIL"]:
          case _actions__WEBPACK_IMPORTED_MODULE_0__["DELETE_TOOL_FAIL"]:
            {
              return Object.assign(Object.assign({}, toolState), {
                error: action.payload,
                loading: false,
                loaded: false
              });
            }

          default:
            {
              return toolState;
            }
        }
      }
      /***/

    },

    /***/
    "FXeW":
    /*!*******************************************!*\
      !*** ./src/app/shared/enums/tolerance.ts ***!
      \*******************************************/

    /*! exports provided: ToleranceEnum */

    /***/
    function FXeW(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ToleranceEnum", function () {
        return ToleranceEnum;
      });

      var ToleranceEnum;

      (function (ToleranceEnum) {
        ToleranceEnum["ABSOLUTE"] = "Absolute";
        ToleranceEnum["RELATIVE"] = "Relative";
      })(ToleranceEnum || (ToleranceEnum = {}));
      /***/

    },

    /***/
    "FmN0":
    /*!******************************************************************************!*\
      !*** ./src/app/shared/components/button-siemens/button-siemens.component.ts ***!
      \******************************************************************************/

    /*! exports provided: ButtonSiemensComponent */

    /***/
    function FmN0(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ButtonSiemensComponent", function () {
        return ButtonSiemensComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      var ButtonSiemensComponent = function ButtonSiemensComponent() {
        _classCallCheck(this, ButtonSiemensComponent);

        this.type = 'button';
      };

      ButtonSiemensComponent.ɵfac = function ButtonSiemensComponent_Factory(t) {
        return new (t || ButtonSiemensComponent)();
      };

      ButtonSiemensComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: ButtonSiemensComponent,
        selectors: [["app-button-siemens"]],
        inputs: {
          text: "text",
          type: "type",
          stylingCondition: "stylingCondition"
        },
        decls: 2,
        vars: 3,
        consts: [[1, "rounded", "text-white", "font-thin", "py-1", "px-4", "border", "border-gray-400", "shadow", "btn", "text-sm", "duration-300", "hover:text-black", "cursor-pointer", 3, "type", "ngClass"]],
        template: function ButtonSiemensComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("type", ctx.type)("ngClass", ctx.stylingCondition);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.text, " ");
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["NgClass"]],
        encapsulation: 2
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ButtonSiemensComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-button-siemens',
            template: "\n    <button\n      class=\"rounded text-white font-thin py-1 px-4 border border-gray-400 shadow btn text-sm duration-300 hover:text-black cursor-pointer\"\n      [type]=\"type\"\n      [ngClass]=\"stylingCondition\"\n    >\n      {{ text }}\n    </button>\n  "
          }]
        }], null, {
          text: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          type: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          stylingCondition: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }]
        });
      })();
      /***/

    },

    /***/
    "KHs+":
    /*!************************************************!*\
      !*** ./src/app/shared/enums/websocket-enum.ts ***!
      \************************************************/

    /*! exports provided: WebsocketEnums */

    /***/
    function KHs(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "WebsocketEnums", function () {
        return WebsocketEnums;
      });

      var WebsocketEnums;

      (function (WebsocketEnums) {
        WebsocketEnums["ProgressBar"] = "/topic/tools/calculation-status";
        WebsocketEnums["NewLog"] = "/topic/logs/new-log";
        WebsocketEnums["NewTool"] = "/topic/plcs/new-tool";
        WebsocketEnums["UsedTool"] = "/topic/plcs/current-tool";
        WebsocketEnums["ConnectionStatus"] = "/topic/plcs/connection-status";
      })(WebsocketEnums || (WebsocketEnums = {}));
      /***/

    },

    /***/
    "PBnp":
    /*!**************************************************!*\
      !*** ./src/app/shared/services/pager.service.ts ***!
      \**************************************************/

    /*! exports provided: PagerService */

    /***/
    function PBnp(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PagerService", function () {
        return PagerService;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var PagerService = /*#__PURE__*/function () {
        function PagerService() {
          _classCallCheck(this, PagerService);
        }

        _createClass(PagerService, [{
          key: "getPager",
          value: function getPager(totalItems) {
            var currentPage = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 1;
            var pageSize = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 10;
            // calculate total pages
            var totalPages = Math.ceil(totalItems / pageSize);
            var startPage;
            var endPage;

            if (totalPages <= 10) {
              // less than 10 total pages so show all
              startPage = 1;
              endPage = totalPages;
            } else {
              // more than 10 total pages so calculate start and end pages
              if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
              } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
              } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
              }
            } // calculate start and end item indexes


            var startIndex = (currentPage - 1) * pageSize;
            var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1); // create an array of pages to ng-repeat in the pager control

            var pages = this.range(startPage, endPage + 1); // return object with all pager properties required by the view

            return {
              totalItems: totalItems,
              currentPage: currentPage,
              pageSize: pageSize,
              totalPages: totalPages,
              startPage: startPage,
              endPage: endPage,
              startIndex: startIndex,
              endIndex: endIndex,
              pages: pages
            };
          }
        }, {
          key: "range",
          value: function range(start, end) {
            var step = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 1;

            if (end === undefined) {
              var _ref = [start, 0];
              end = _ref[0];
              start = _ref[1];
            }

            for (var n = start; n < end; n += step) {
              return n;
            }
          }
        }]);

        return PagerService;
      }();

      PagerService.ɵfac = function PagerService_Factory(t) {
        return new (t || PagerService)();
      };

      PagerService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: PagerService,
        factory: PagerService.ɵfac,
        providedIn: 'root'
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PagerService, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
          args: [{
            providedIn: 'root'
          }]
        }], function () {
          return [];
        }, null);
      })();
      /***/

    },

    /***/
    "PCNd":
    /*!*****************************************!*\
      !*** ./src/app/shared/shared.module.ts ***!
      \*****************************************/

    /*! exports provided: SharedModule */

    /***/
    function PCNd(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "SharedModule", function () {
        return SharedModule;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _components_pager_pager_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./components/pager/pager.component */
      "a4eG");
      /* harmony import */


      var _components_table_table_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./components/table/table.component */
      "Xv+k");
      /* harmony import */


      var _components_button_button_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ./components/button/button.component */
      "VkHG");
      /* harmony import */


      var _components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! ./components/no-data/no-data.component */
      "sIB3");
      /* harmony import */


      var _components_button_siemens_button_siemens_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! ./components/button-siemens/button-siemens.component */
      "FmN0");
      /* harmony import */


      var _directives_click_outside_directive__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(
      /*! ./directives/click-outside.directive */
      "jYWo");
      /* harmony import */


      var _modules_modal_modal_module__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(
      /*! ./modules/modal/modal.module */
      "h3XU");
      /* harmony import */


      var _components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(
      /*! ./components/dropdown/dropdown.component */
      "4yaq");
      /* harmony import */


      var _components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(
      /*! ./components/plc-list/plc-list.component */
      "lOC7");
      /* harmony import */


      var _components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(
      /*! ./components/busy-indicator/busy-indicator.component */
      "xy3U");

      var SharedModule = function SharedModule() {
        _classCallCheck(this, SharedModule);
      };

      SharedModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({
        type: SharedModule
      });
      SharedModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({
        factory: function SharedModule_Factory(t) {
          return new (t || SharedModule)();
        },
        imports: [[_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]], _modules_modal_modal_module__WEBPACK_IMPORTED_MODULE_8__["ModalModule"]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](SharedModule, {
          declarations: [_components_pager_pager_component__WEBPACK_IMPORTED_MODULE_2__["PagerComponent"], _components_table_table_component__WEBPACK_IMPORTED_MODULE_3__["TableComponent"], _components_button_button_component__WEBPACK_IMPORTED_MODULE_4__["ButtonComponent"], _components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__["NoDataComponent"], _components_button_siemens_button_siemens_component__WEBPACK_IMPORTED_MODULE_6__["ButtonSiemensComponent"], _directives_click_outside_directive__WEBPACK_IMPORTED_MODULE_7__["ClickOutsideDirective"], _components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_9__["DropdownComponent"], _components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_10__["PlcListComponent"], _components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_11__["BusyIndicatorComponent"]],
          imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]],
          exports: [_modules_modal_modal_module__WEBPACK_IMPORTED_MODULE_8__["ModalModule"], _components_pager_pager_component__WEBPACK_IMPORTED_MODULE_2__["PagerComponent"], _components_table_table_component__WEBPACK_IMPORTED_MODULE_3__["TableComponent"], _components_button_button_component__WEBPACK_IMPORTED_MODULE_4__["ButtonComponent"], _components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__["NoDataComponent"], _components_button_siemens_button_siemens_component__WEBPACK_IMPORTED_MODULE_6__["ButtonSiemensComponent"], _components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_9__["DropdownComponent"], _components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_10__["PlcListComponent"], _components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_11__["BusyIndicatorComponent"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](SharedModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
          args: [{
            declarations: [_components_pager_pager_component__WEBPACK_IMPORTED_MODULE_2__["PagerComponent"], _components_table_table_component__WEBPACK_IMPORTED_MODULE_3__["TableComponent"], _components_button_button_component__WEBPACK_IMPORTED_MODULE_4__["ButtonComponent"], _components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__["NoDataComponent"], _components_button_siemens_button_siemens_component__WEBPACK_IMPORTED_MODULE_6__["ButtonSiemensComponent"], _directives_click_outside_directive__WEBPACK_IMPORTED_MODULE_7__["ClickOutsideDirective"], _components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_9__["DropdownComponent"], _components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_10__["PlcListComponent"], _components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_11__["BusyIndicatorComponent"]],
            imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]],
            exports: [_modules_modal_modal_module__WEBPACK_IMPORTED_MODULE_8__["ModalModule"], _components_pager_pager_component__WEBPACK_IMPORTED_MODULE_2__["PagerComponent"], _components_table_table_component__WEBPACK_IMPORTED_MODULE_3__["TableComponent"], _components_button_button_component__WEBPACK_IMPORTED_MODULE_4__["ButtonComponent"], _components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__["NoDataComponent"], _components_button_siemens_button_siemens_component__WEBPACK_IMPORTED_MODULE_6__["ButtonSiemensComponent"], _components_dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_9__["DropdownComponent"], _components_plc_list_plc_list_component__WEBPACK_IMPORTED_MODULE_10__["PlcListComponent"], _components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_11__["BusyIndicatorComponent"]]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "RxZS":
    /*!****************************************!*\
      !*** ./src/app/store/effects/index.ts ***!
      \****************************************/

    /*! exports provided: PlcEffect, ToolEffects */

    /***/
    function RxZS(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _plc_effects__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./plc.effects */
      "v47b");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "PlcEffect", function () {
        return _plc_effects__WEBPACK_IMPORTED_MODULE_0__["PlcEffect"];
      });
      /* harmony import */


      var _tool_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./tool.effects */
      "xu6W");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "ToolEffects", function () {
        return _tool_effects__WEBPACK_IMPORTED_MODULE_1__["ToolEffects"];
      });
      /***/

    },

    /***/
    "Sy1n":
    /*!**********************************!*\
      !*** ./src/app/app.component.ts ***!
      \**********************************/

    /*! exports provided: AppComponent */

    /***/
    function Sy1n(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "AppComponent", function () {
        return AppComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _sharedEnums__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @sharedEnums */
      "k5Aa");
      /* harmony import */


      var _shared_animations_route_animations__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./shared/animations/route-animations */
      "gn+R");
      /* harmony import */


      var _shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./shared/websocket/websocket.service */
      "to+R");
      /* harmony import */


      var _layout_components_header_header_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ./layout/components/header/header.component */
      "/3YG");
      /* harmony import */


      var _shared_components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! ./shared/components/busy-indicator/busy-indicator.component */
      "xy3U");
      /* harmony import */


      var _layout_components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! ./layout/components/sidebar/sidebar.component */
      "eSv6");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");

      var AppComponent = /*#__PURE__*/function () {
        function AppComponent(websocket) {
          _classCallCheck(this, AppComponent);

          this.websocket = websocket;
        }

        _createClass(AppComponent, [{
          key: "ngOnInit",
          value: function ngOnInit() {
            var _this2 = this;

            this.websocket.connect();
            setTimeout(function () {
              return _this2.websocket.sub(_sharedEnums__WEBPACK_IMPORTED_MODULE_1__["WebsocketEnums"].NewTool, true);
            }, 550);
            setTimeout(function () {
              return _this2.websocket.sub(_sharedEnums__WEBPACK_IMPORTED_MODULE_1__["WebsocketEnums"].UsedTool, true);
            }, 700);
          }
        }, {
          key: "ngOnDestroy",
          value: function ngOnDestroy() {
            this.websocket.disconnect();
            this.websocket.unsub();
          }
        }, {
          key: "prepareRoute",
          value: function prepareRoute(outlet) {
            return outlet.isActivated ? outlet.activatedRoute : '';
          }
        }]);

        return AppComponent;
      }();

      AppComponent.ɵfac = function AppComponent_Factory(t) {
        return new (t || AppComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_3__["WebsocketService"]));
      };

      AppComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: AppComponent,
        selectors: [["app-root"]],
        decls: 8,
        vars: 2,
        consts: [[1, "dashboard", "m-auto", "flex", "h-full", "overflow-hidden", "relative"], [3, "color"], [1, "w-full", "h-full", "overflow-hidden"], [1, "w-full", "h-full"], ["outlet", "outlet"]],
        template: function AppComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-header");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "main", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "busy-indicator", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](3, "app-sidebar");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "div", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](6, "router-outlet", null, 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            var _r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("color", "dark");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("@routeAnimations", ctx.prepareRoute(_r0));
          }
        },
        directives: [_layout_components_header_header_component__WEBPACK_IMPORTED_MODULE_4__["HeaderComponent"], _shared_components_busy_indicator_busy_indicator_component__WEBPACK_IMPORTED_MODULE_5__["BusyIndicatorComponent"], _layout_components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_6__["SidebarComponent"], _angular_router__WEBPACK_IMPORTED_MODULE_7__["RouterOutlet"]],
        styles: [".dashboard[_ngcontent-%COMP%]{height: calc(100vh - 52px)}"],
        data: {
          animation: [_shared_animations_route_animations__WEBPACK_IMPORTED_MODULE_2__["fader"]]
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](AppComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-root',
            template: "\n    <app-header></app-header>\n\n    <main class=\"dashboard m-auto flex h-full overflow-hidden relative\">\n      <busy-indicator [color]=\"'dark'\"></busy-indicator>\n\n      <app-sidebar></app-sidebar>\n      <div class=\"w-full h-full overflow-hidden \">\n        <div class=\"w-full h-full\" [@routeAnimations]=\"prepareRoute(outlet)\">\n          <router-outlet #outlet=\"outlet\"></router-outlet>\n        </div>\n      </div>\n      <!--\n      <div class=\"z-0 absolute right-0 top-2 p-6\">\n         <app-notification-queue></app-notification-queue>\n      </div>\n      -->\n    </main>\n  ",
            styles: ['.dashboard{height: calc(100vh - 52px)}'],
            animations: [_shared_animations_route_animations__WEBPACK_IMPORTED_MODULE_2__["fader"]]
          }]
        }], function () {
          return [{
            type: _shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_3__["WebsocketService"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "Tx//":
    /*!*****************************************!*\
      !*** ./src/app/layout/layout.module.ts ***!
      \*****************************************/

    /*! exports provided: LayoutModule */

    /***/
    function Tx(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LayoutModule", function () {
        return LayoutModule;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");
      /* harmony import */


      var _components_header_header_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./components/header/header.component */
      "/3YG");
      /* harmony import */


      var _components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ./components/sidebar/sidebar.component */
      "eSv6");

      var LayoutModule = function LayoutModule() {
        _classCallCheck(this, LayoutModule);
      };

      LayoutModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({
        type: LayoutModule
      });
      LayoutModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({
        factory: function LayoutModule_Factory(t) {
          return new (t || LayoutModule)();
        },
        imports: [[_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](LayoutModule, {
          declarations: [_components_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_4__["SidebarComponent"]],
          imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]],
          exports: [_components_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_4__["SidebarComponent"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](LayoutModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
          args: [{
            declarations: [_components_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_4__["SidebarComponent"]],
            imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]],
            exports: [_components_header_header_component__WEBPACK_IMPORTED_MODULE_3__["HeaderComponent"], _components_sidebar_sidebar_component__WEBPACK_IMPORTED_MODULE_4__["SidebarComponent"]]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "UH8I":
    /*!**************************************************!*\
      !*** ./src/app/shared/services/tools.service.ts ***!
      \**************************************************/

    /*! exports provided: ToolsService */

    /***/
    function UH8I(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ToolsService", function () {
        return ToolsService;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common/http */
      "tk/3");
      /* harmony import */


      var src_environments_environment__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! src/environments/environment */
      "AytR");

      var ToolsService = /*#__PURE__*/function () {
        function ToolsService(http) {
          _classCallCheck(this, ToolsService);

          this.http = http;
        }

        _createClass(ToolsService, [{
          key: "getTools",
          value: function getTools() {
            return this.http.get("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/tools"));
          }
        }, {
          key: "getToolsByPlc",
          value: function getToolsByPlc(id) {
            return this.http.get("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(id, "/tools"));
          }
        }, {
          key: "createTool",
          value: function createTool(tool) {
            return this.http.post("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(tool.plcId, "/tools"), tool);
          }
        }, {
          key: "updateTool",
          value: function updateTool(tool) {
            return this.http.put("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(tool.plcId, "/tools/").concat(tool.id), tool);
          }
        }, {
          key: "deleteTool",
          value: function deleteTool(tool) {
            return this.http["delete"]("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(tool.plcId, "/tools/").concat(tool.id));
          }
        }]);

        return ToolsService;
      }();

      ToolsService.ɵfac = function ToolsService_Factory(t) {
        return new (t || ToolsService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]));
      };

      ToolsService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: ToolsService,
        factory: ToolsService.ɵfac,
        providedIn: 'root'
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ToolsService, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
          args: [{
            providedIn: 'root'
          }]
        }], function () {
          return [{
            type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "UQwZ":
    /*!**********************************************!*\
      !*** ./src/app/store/actions/tool.action.ts ***!
      \**********************************************/

    /*! exports provided: CREATE_TOOL, CREATE_TOOL_SUCCESS, CREATE_TOOL_FAIL, CreateTool, CreateToolSuccess, CreateToolFail, LOAD_TOOLS, LOAD_TOOLS_SUCCESS, LOAD_TOOLS_FAIL, LoadTools, LoadToolsSuccess, LoadToolsFail, UPDATE_TOOL, UPDATE_TOOL_SUCCESS, UPDATE_TOOL_FAIL, UpdateTool, UpdateToolSuccess, UpdateToolFail, DELETE_TOOL, DELETE_TOOL_SUCCESS, DELETE_TOOL_FAIL, DeleteTool, DeleteToolSuccess, DeleteToolFail, ADD_NEW_TOOL, AddNewTool, CURRENTLY_USED, CurrentlyUsed, LOAD_TOOLS_PLC, LOAD_TOOLS_PLC_SUCCESS, LOAD_TOOLS_PLC_FAIL, LoadToolsPLC, LoadToolsPLCSuccess, LoadToolsPLCFail */

    /***/
    function UQwZ(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL", function () {
        return CREATE_TOOL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL_SUCCESS", function () {
        return CREATE_TOOL_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL_FAIL", function () {
        return CREATE_TOOL_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreateTool", function () {
        return CreateTool;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreateToolSuccess", function () {
        return CreateToolSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CreateToolFail", function () {
        return CreateToolFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS", function () {
        return LOAD_TOOLS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_SUCCESS", function () {
        return LOAD_TOOLS_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_FAIL", function () {
        return LOAD_TOOLS_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadTools", function () {
        return LoadTools;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsSuccess", function () {
        return LoadToolsSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsFail", function () {
        return LoadToolsFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL", function () {
        return UPDATE_TOOL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL_SUCCESS", function () {
        return UPDATE_TOOL_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL_FAIL", function () {
        return UPDATE_TOOL_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdateTool", function () {
        return UpdateTool;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdateToolSuccess", function () {
        return UpdateToolSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "UpdateToolFail", function () {
        return UpdateToolFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL", function () {
        return DELETE_TOOL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL_SUCCESS", function () {
        return DELETE_TOOL_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL_FAIL", function () {
        return DELETE_TOOL_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DeleteTool", function () {
        return DeleteTool;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DeleteToolSuccess", function () {
        return DeleteToolSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DeleteToolFail", function () {
        return DeleteToolFail;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ADD_NEW_TOOL", function () {
        return ADD_NEW_TOOL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "AddNewTool", function () {
        return AddNewTool;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CURRENTLY_USED", function () {
        return CURRENTLY_USED;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "CurrentlyUsed", function () {
        return CurrentlyUsed;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC", function () {
        return LOAD_TOOLS_PLC;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC_SUCCESS", function () {
        return LOAD_TOOLS_PLC_SUCCESS;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC_FAIL", function () {
        return LOAD_TOOLS_PLC_FAIL;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLC", function () {
        return LoadToolsPLC;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLCSuccess", function () {
        return LoadToolsPLCSuccess;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLCFail", function () {
        return LoadToolsPLCFail;
      });

      var CREATE_TOOL = '[Tool] Create tool';
      var CREATE_TOOL_SUCCESS = '[Tool] Create tool success';
      var CREATE_TOOL_FAIL = '[Tool] Create tool fail';

      var CreateTool = function CreateTool(payload) {
        _classCallCheck(this, CreateTool);

        this.payload = payload;
        this.type = CREATE_TOOL;
      };

      var CreateToolSuccess = function CreateToolSuccess(payload) {
        _classCallCheck(this, CreateToolSuccess);

        this.payload = payload;
        this.type = CREATE_TOOL_SUCCESS;
      };

      var CreateToolFail = function CreateToolFail(payload) {
        _classCallCheck(this, CreateToolFail);

        this.payload = payload;
        this.type = CREATE_TOOL_FAIL;
      };

      var LOAD_TOOLS = '[Tool] Load tools';
      var LOAD_TOOLS_SUCCESS = '[Tool] Load tools success';
      var LOAD_TOOLS_FAIL = '[Tool] Load tools fail';

      var LoadTools = function LoadTools() {
        _classCallCheck(this, LoadTools);

        this.type = LOAD_TOOLS;
      };

      var LoadToolsSuccess = function LoadToolsSuccess(payload) {
        _classCallCheck(this, LoadToolsSuccess);

        this.payload = payload;
        this.type = LOAD_TOOLS_SUCCESS;
      };

      var LoadToolsFail = function LoadToolsFail(payload) {
        _classCallCheck(this, LoadToolsFail);

        this.payload = payload;
        this.type = LOAD_TOOLS_FAIL;
      };

      var UPDATE_TOOL = '[Tool] Update tool';
      var UPDATE_TOOL_SUCCESS = '[Tool] Update tool success';
      var UPDATE_TOOL_FAIL = '[Tool] Update tool fail';

      var UpdateTool = function UpdateTool(payload) {
        _classCallCheck(this, UpdateTool);

        this.payload = payload;
        this.type = UPDATE_TOOL;
      };

      var UpdateToolSuccess = function UpdateToolSuccess(payload) {
        _classCallCheck(this, UpdateToolSuccess);

        this.payload = payload;
        this.type = UPDATE_TOOL_SUCCESS;
      };

      var UpdateToolFail = function UpdateToolFail(payload) {
        _classCallCheck(this, UpdateToolFail);

        this.payload = payload;
        this.type = UPDATE_TOOL_FAIL;
      };

      var DELETE_TOOL = '[Tool] Delete tool';
      var DELETE_TOOL_SUCCESS = '[Tool] Delete tool success';
      var DELETE_TOOL_FAIL = '[Tool] Delete tool fail';

      var DeleteTool = function DeleteTool(payload) {
        _classCallCheck(this, DeleteTool);

        this.payload = payload;
        this.type = DELETE_TOOL;
      };

      var DeleteToolSuccess = function DeleteToolSuccess(payload) {
        _classCallCheck(this, DeleteToolSuccess);

        this.payload = payload;
        this.type = DELETE_TOOL_SUCCESS;
      };

      var DeleteToolFail = function DeleteToolFail(payload) {
        _classCallCheck(this, DeleteToolFail);

        this.payload = payload;
        this.type = DELETE_TOOL_FAIL;
      };

      var ADD_NEW_TOOL = '[Tool] Add new tool';

      var AddNewTool = function AddNewTool(payload) {
        _classCallCheck(this, AddNewTool);

        this.payload = payload;
        this.type = ADD_NEW_TOOL;
      };

      var CURRENTLY_USED = '[Tool] Currently used tool';

      var CurrentlyUsed = function CurrentlyUsed(payload) {
        _classCallCheck(this, CurrentlyUsed);

        this.payload = payload;
        this.type = CURRENTLY_USED;
      };

      var LOAD_TOOLS_PLC = '[Tool] Load tools for plc';
      var LOAD_TOOLS_PLC_SUCCESS = '[Tool] Load tools for plc success';
      var LOAD_TOOLS_PLC_FAIL = '[Tool] Load tools for plc fail';

      var LoadToolsPLC = function LoadToolsPLC(payload) {
        _classCallCheck(this, LoadToolsPLC);

        this.payload = payload;
        this.type = LOAD_TOOLS_PLC;
      };

      var LoadToolsPLCSuccess = function LoadToolsPLCSuccess(payload) {
        _classCallCheck(this, LoadToolsPLCSuccess);

        this.payload = payload;
        this.type = LOAD_TOOLS_PLC_SUCCESS;
      };

      var LoadToolsPLCFail = function LoadToolsPLCFail(payload) {
        _classCallCheck(this, LoadToolsPLCFail);

        this.payload = payload;
        this.type = LOAD_TOOLS_PLC_FAIL;
      };
      /***/

    },

    /***/
    "VRmN":
    /*!*******************************************************************!*\
      !*** ./src/app/shared/modules/modal/container/modal.component.ts ***!
      \*******************************************************************/

    /*! exports provided: ModalComponent */

    /***/
    function VRmN(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ModalComponent", function () {
        return ModalComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_animations__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/animations */
      "R0Ic");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      var _c0 = ["modalBody"];

      function ModalComponent_div_0_ng_container_5_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainer"](0);
        }
      }

      function ModalComponent_div_0_Template(rf, ctx) {
        if (rf & 1) {
          var _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ModalComponent_div_0_Template_div_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r3);

            var ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            return ctx_r2.close(true);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "div", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "div", 5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ModalComponent_div_0_Template_div_click_4_listener($event) {
            return $event.stopPropagation();
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, ModalComponent_div_0_ng_container_5_Template, 1, 0, "ng-container", 6);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("@fade", undefined);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngTemplateOutlet", ctx_r0.body);
        }
      }

      var ModalComponent = /*#__PURE__*/function () {
        function ModalComponent(elementRef, changeDetectorRef) {
          _classCallCheck(this, ModalComponent);

          this.elementRef = elementRef;
          this.changeDetectorRef = changeDetectorRef;
          this.closeOnOutsideClick = true;
          this.closeEvent = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.visible = false;
        }

        _createClass(ModalComponent, [{
          key: "ngOnDestroy",
          value: function ngOnDestroy() {
            this.close();
          }
        }, {
          key: "open",
          value: function open() {
            this.visible = true;
          }
        }, {
          key: "close",
          value: function close() {
            var _this3 = this;

            var fromOutside = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

            if (fromOutside && !this.closeOnOutsideClick) {
              return;
            }

            this.visible = false;
            this.changeDetectorRef.markForCheck();
            setTimeout(function () {
              return _this3.closeEvent.emit();
            }, 150);
          }
        }, {
          key: "onKeyDownHandler",
          value: function onKeyDownHandler(event) {
            if (event.key === 'Escape' && this.isTopMost()) {
              this.close();
            }
          }
        }, {
          key: "isTopMost",
          value: function isTopMost() {
            return !this.elementRef.nativeElement.querySelector(':scope modal > .modal');
          }
        }]);

        return ModalComponent;
      }();

      ModalComponent.ɵfac = function ModalComponent_Factory(t) {
        return new (t || ModalComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ChangeDetectorRef"]));
      };

      ModalComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: ModalComponent,
        selectors: [["app-modal"]],
        contentQueries: function ModalComponent_ContentQueries(rf, ctx, dirIndex) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵcontentQuery"](dirIndex, _c0, 1);
          }

          if (rf & 2) {
            var _t;

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.body = _t.first);
          }
        },
        hostBindings: function ModalComponent_HostBindings(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("keydown", function ModalComponent_keydown_HostBindingHandler($event) {
              return ctx.onKeyDownHandler($event);
            }, false, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵresolveDocument"]);
          }
        },
        inputs: {
          closeOnOutsideClick: "closeOnOutsideClick"
        },
        outputs: {
          closeEvent: "closeEvent"
        },
        decls: 1,
        vars: 1,
        consts: [["class", "modal fixed bottom-0 p-4 pb-4 inset-0 flex items-center justify-center", "role", "dialog", 3, "click", 4, "ngIf"], ["role", "dialog", 1, "modal", "fixed", "bottom-0", "p-4", "pb-4", "inset-0", "flex", "items-center", "justify-center", 3, "click"], [1, "fixed", "inset-0", "transition-opacity"], [1, "absolute", "inset-0", "bg-gray-900", "opacity-50"], ["role", "dialog", "aria-modal", "true", "aria-labelledby", "modal-headline", 1, "bg-white", "rounded-lg", "overflow-hidden", "shadow-xl", "transform", "transition-all"], [1, "modal-body", 3, "click"], [4, "ngTemplateOutlet"]],
        template: function ModalComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, ModalComponent_div_0_Template, 6, 2, "div", 0);
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.visible);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgTemplateOutlet"]],
        encapsulation: 2,
        data: {
          animation: [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["trigger"])('fade', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["transition"])('void => *', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            opacity: 0
          }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])(150, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            opacity: 1
          }))]), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["transition"])('* => void', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])(150, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
            opacity: 0
          }))])])]
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ModalComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-modal',
            template: "\n    <div\n      class=\"modal fixed bottom-0 p-4 pb-4 inset-0 flex items-center justify-center\"\n      role=\"dialog\"\n      *ngIf=\"visible\"\n      (click)=\"close(true)\"\n      @fade\n    >\n      <div class=\"fixed inset-0 transition-opacity\">\n        <div class=\"absolute inset-0 bg-gray-900 opacity-50\"></div>\n      </div>\n      <div\n        class=\"bg-white rounded-lg overflow-hidden shadow-xl transform transition-all\"\n        role=\"dialog\"\n        aria-modal=\"true\"\n        aria-labelledby=\"modal-headline\"\n      >\n        <div class=\"modal-body\" (click)=\"$event.stopPropagation()\">\n          <ng-container *ngTemplateOutlet=\"body\"></ng-container>\n        </div>\n      </div>\n    </div>\n  ",
            animations: [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["trigger"])('fade', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["transition"])('void => *', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
              opacity: 0
            }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])(150, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
              opacity: 1
            }))]), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["transition"])('* => void', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["animate"])(150, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_1__["style"])({
              opacity: 0
            }))])])]
          }]
        }], function () {
          return [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"]
          }, {
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ChangeDetectorRef"]
          }];
        }, {
          body: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ContentChild"],
            args: ['modalBody']
          }],
          closeOnOutsideClick: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          closeEvent: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }],
          onKeyDownHandler: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"],
            args: ['document:keydown', ['$event']]
          }]
        });
      })();
      /***/

    },

    /***/
    "VkHG":
    /*!**************************************************************!*\
      !*** ./src/app/shared/components/button/button.component.ts ***!
      \**************************************************************/

    /*! exports provided: ButtonComponent */

    /***/
    function VkHG(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ButtonComponent", function () {
        return ButtonComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      var _c0 = function _c0(a0) {
        return [a0];
      };

      function ButtonComponent_i_1_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "i", 2);
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵclassProp"]("mr-2", !!ctx_r0.text);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction1"](3, _c0, ctx_r0.icon));
        }
      }

      var _c1 = function _c1(a0, a1, a2, a3, a4) {
        return {
          "bg-blue-500 hover:bg-blue-400 font-medium": a0,
          "bg-gray-300 hover:bg-gray-400 text-gray-800 shadow-none font-medium": a1,
          "bg-red-600 hover:bg-red-500 font-medium": a2,
          "text-gray-800 font-normal shadow-none hover:bg-gray-600 hover:text-white": a3,
          "text-gray-500 cursor-not-allowed": a4
        };
      };

      var ButtonComponent = function ButtonComponent() {
        _classCallCheck(this, ButtonComponent);

        this.type = 'primary';
      };

      ButtonComponent.ɵfac = function ButtonComponent_Factory(t) {
        return new (t || ButtonComponent)();
      };

      ButtonComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: ButtonComponent,
        selectors: [["app-button"]],
        inputs: {
          text: "text",
          title: ["customTitle", "title"],
          icon: "icon",
          type: "type"
        },
        decls: 4,
        vars: 10,
        consts: [[1, "py-2", "px-4", "rounded-md", "inline-flex", "items-center", "duration-300", "whitespace-no-wrap", "outline-none", "focus:outline-none", "text-sm", "border", "border-transparent", "text-white", 3, "ngClass", "title"], ["class", "fa", 3, "ngClass", "mr-2", 4, "ngIf"], [1, "fa", 3, "ngClass"]],
        template: function ButtonComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, ButtonComponent_i_1_Template, 1, 5, "i", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "span");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction5"](4, _c1, ctx.type === "primary", ctx.type === "secondary", ctx.type === "danger", ctx.type === "none", ctx.type === "disabled"))("title", ctx.title);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.icon);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.text);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgIf"]],
        encapsulation: 2
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ButtonComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-button',
            template: "\n    <button\n      class=\"py-2 px-4 rounded-md inline-flex items-center duration-300 whitespace-no-wrap outline-none focus:outline-none text-sm  border border-transparent text-white\"\n      [ngClass]=\"{\n        'bg-blue-500 hover:bg-blue-400 font-medium': type === 'primary',\n        'bg-gray-300 hover:bg-gray-400 text-gray-800 shadow-none font-medium': type === 'secondary',\n        'bg-red-600 hover:bg-red-500 font-medium': type === 'danger',\n        'text-gray-800 font-normal shadow-none hover:bg-gray-600 hover:text-white': type === 'none',\n        'text-gray-500 cursor-not-allowed': type === 'disabled'\n      }\"\n      [title]=\"title\"\n    >\n      <i *ngIf=\"icon\" class=\"fa\" [ngClass]=\"[icon]\" [class.mr-2]=\"!!text\"></i>\n\n      <span>{{ text }}</span>\n    </button>\n  "
          }]
        }], null, {
          text: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          title: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"],
            args: ['customTitle']
          }],
          icon: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          type: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }]
        });
      })();
      /***/

    },

    /***/
    "Xv+k":
    /*!************************************************************!*\
      !*** ./src/app/shared/components/table/table.component.ts ***!
      \************************************************************/

    /*! exports provided: TableComponent */

    /***/
    function XvK(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "TableComponent", function () {
        return TableComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _services_pager_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ../../services/pager.service */
      "PBnp");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _button_button_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ../button/button.component */
      "VkHG");
      /* harmony import */


      var _dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ../dropdown/dropdown.component */
      "4yaq");
      /* harmony import */


      var _no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! ../no-data/no-data.component */
      "sIB3");

      var _c0 = function _c0(a0, a1) {
        return {
          "fa-caret-down": a0,
          "fa-caret-up": a1
        };
      };

      function TableComponent_table_0_ng_container_3_i_3_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "i", 7);
        }

        if (rf & 2) {
          var col_r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](1, _c0, col_r6.descending, !col_r6.descending));
        }
      }

      var _c1 = function _c1(a0, a1) {
        return {
          "text-blue-600": a0,
          "cursor-pointer": a1
        };
      };

      function TableComponent_table_0_ng_container_3_Template(rf, ctx) {
        if (rf & 1) {
          var _r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "th", 5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_3_Template_th_click_1_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r10);

            var col_r6 = ctx.$implicit;

            var ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return col_r6.name ? ctx_r9.onAction("sort", col_r6) : null;
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, TableComponent_table_0_ng_container_3_i_3_Template, 1, 4, "i", 6);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r6 = ctx.$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](3, _c1, col_r6.sort_by, col_r6.name));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", col_r6.name, " ");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", col_r6.sort_by);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_2_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpipe"](2, "date");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpipeBind2"](2, 1, row_r11[col_r14.property_name], "dd.MM.yyyy HH:mm:ss"), " ");
        }
      }

      var _c2 = function _c2(a0, a1) {
        return {
          "text-red-600": a0,
          "text-green-600": a1
        };
      };

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_3_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "span", 12);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "i", 13);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var ctx_r16 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](2, _c2, row_r11[col_r14.property_name] === "DISCONNECTED", row_r11[col_r14.property_name] === "CONNECTED"));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("title", ctx_r16.capitalizeFirst(row_r11[col_r14.property_name]));
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_4_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 14);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "label", 15);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](3, "input", 16);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("checked", row_r11[col_r14.property_name]);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵattribute"]("disabled", true);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_div_1_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 25);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "i", 26);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_2_Template(rf, ctx) {
        if (rf & 1) {
          var _r41 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-button", 27);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_2_Template_app_button_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r41);

            var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

            var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

            var ctx_r39 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r39.onAction(action_r29.type, row_r11);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", action_r29.text)("icon", action_r29.icon)("type", "none")("customTitle", action_r29.title);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_3_Template(rf, ctx) {
        if (rf & 1) {
          var _r46 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-button", 27);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_3_Template_app_button_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r46);

            var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

            var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

            var ctx_r44 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r44.onAction(action_r29.type, row_r11);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", action_r29.text)("icon", "fa-stop")("type", "none")("customTitle", "Stop automatic monitoring");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_4_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-button", 28);
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", action_r29.text)("icon", action_r29.icon)("type", "disabled")("customTitle", action_r29.title);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_5_app_button_0_Template(rf, ctx) {
        if (rf & 1) {
          var _r53 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-button", 27);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_5_app_button_0_Template_app_button_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r53);

            var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2).$implicit;

            var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

            var ctx_r51 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r51.onAction(action_r29.type, row_r11);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2).$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", action_r29.text)("icon", action_r29.icon)("type", "none")("customTitle", action_r29.title);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_5_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_5_app_button_0_Template, 1, 4, "app-button", 20);
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type !== "start" && action_r29.type !== "checkbox" && action_r29.type !== "reference");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_7_app_button_0_Template(rf, ctx) {
        if (rf & 1) {
          var _r60 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-button", 27);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_7_app_button_0_Template_app_button_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r60);

            var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2).$implicit;

            var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

            var ctx_r58 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r58.onAction(action_r29.type, row_r11);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2).$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", action_r29.text)("icon", action_r29.icon)("type", "none")("customTitle", action_r29.title);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_7_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_7_app_button_0_Template, 1, 4, "app-button", 20);
        }

        if (rf & 2) {
          var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "reference" && row_r11.tolerance);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_label_9_Template(rf, ctx) {
        if (rf & 1) {
          var _r67 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "label", 29);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "input", 30);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_label_9_Template_input_click_1_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r67);

            var action_r29 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

            var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

            var ctx_r65 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            ctx_r65.onAction(action_r29.type, row_r11);
            return row_r11.checked = !row_r11.checked;
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](4).$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("checked", row_r11 == null ? null : row_r11.checked);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_div_1_Template, 2, 0, "div", 18);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_2_Template, 1, 4, "app-button", 19);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_3_Template, 1, 4, "app-button", 20);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](4, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_app_button_4_Template, 1, 4, "app-button", 21);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_5_Template, 1, 1, "ng-template", null, 22, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_ng_template_7_Template, 1, 1, "ng-template", null, 23, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](9, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_label_9_Template, 2, 1, "label", 24);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var action_r29 = ctx.$implicit;

          var _r34 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](6);

          var _r36 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](8);

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3).$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "start" && !row_r11.referenceCurveIsCalculated);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "start" && row_r11.referenceCurveIsCalculated && !row_r11.automaticMonitoring)("ngIfElse", _r34);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "start" && row_r11.referenceCurveIsCalculated && row_r11.automaticMonitoring);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "reference" && !row_r11.tolerance)("ngIfElse", _r36);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", action_r29.type === "checkbox");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 17);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_ng_container_2_Template, 10, 7, "ng-container", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", col_r14.actions);
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_6_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", row_r11[col_r14.property_name] && row_r11[col_r14.property_name][col_r14.nested_property], " ");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_7_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "span", 31);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](2, _c2, row_r11[col_r14.property_name] === false, row_r11[col_r14.property_name] === true));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](row_r11[col_r14.property_name] ? "\u2713" : "\u2715");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_ng_container_8_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var row_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", row_r11[col_r14.property_name], " ");
        }
      }

      function TableComponent_table_0_ng_container_5_ng_container_2_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "td", 9);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_2_Template, 3, 4, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_3_Template, 3, 5, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](4, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_4_Template, 4, 2, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_5_Template, 3, 1, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](6, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_6_Template, 2, 1, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_7_Template, 3, 5, "ng-container", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](8, TableComponent_table_0_ng_container_5_ng_container_2_ng_container_8_Template, 2, 1, "ng-container", 11);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var col_r14 = ctx.$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitch", col_r14.cell_type);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "date");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "status");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "checkbox");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "action");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "nested-text");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngSwitchCase", "check-mark");
        }
      }

      var _c3 = function _c3(a0, a1) {
        return {
          "bg-gray-100": a0,
          "bg-gray-400 font-semibold": a1
        };
      };

      function TableComponent_table_0_ng_container_5_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "tr", 8);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, TableComponent_table_0_ng_container_5_ng_container_2_Template, 9, 7, "ng-container", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var row_r11 = ctx.$implicit;
          var i_r12 = ctx.index;

          var ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](2, _c3, i_r12 % 2 === 1, row_r11 == null ? null : row_r11.isActive));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r4.columns);
        }
      }

      function TableComponent_table_0_tfoot_6_span_5_ng_container_1_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var page_r79 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", page_r79, " ");
        }
      }

      function TableComponent_table_0_tfoot_6_span_5_Template(rf, ctx) {
        if (rf & 1) {
          var _r83 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "span", 37);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TableComponent_table_0_tfoot_6_span_5_Template_span_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r83);

            var page_r79 = ctx.$implicit;

            var ctx_r82 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3);

            return ctx_r82.setPage(page_r79, ctx_r82.pageSize);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, TableComponent_table_0_tfoot_6_span_5_ng_container_1_Template, 2, 1, "ng-container", 4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var page_r79 = ctx.$implicit;

          var ctx_r78 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", page_r79 === ctx_r78.pager.currentPage && "font-bold text-gray-800");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r78.pager.totalPages > 1);
        }
      }

      function TableComponent_table_0_tfoot_6_Template(rf, ctx) {
        if (rf & 1) {
          var _r85 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "tfoot");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "tr");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "td");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "tr", 32);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "td", 33);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, TableComponent_table_0_tfoot_6_span_5_Template, 2, 2, "span", 34);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](6, "td");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "td", 35);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "app-dropdown", 36);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("select", function TableComponent_table_0_tfoot_6_Template_app_dropdown_select_8_listener($event) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r85);

            var ctx_r84 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r84.changePageSize($event);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r5.pagerPages);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵattribute"]("colspan", ctx_r5.columns.length - 2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleMap"]("form");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("text", "Items per page")("items", ctx_r5.pageSizeOptions)("selected", ctx_r5.pageSize)("width", "w-48");
        }
      }

      function TableComponent_table_0_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "table", 2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "thead");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "tr");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, TableComponent_table_0_ng_container_3_Template, 4, 6, "ng-container", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "tbody");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, TableComponent_table_0_ng_container_5_Template, 3, 5, "ng-container", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](6, TableComponent_table_0_tfoot_6_Template, 9, 8, "tfoot", 4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.columns);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.items);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r0.items.length && ctx_r0.pager.totalPages > 1);
        }
      }

      function TableComponent_ng_template_1_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-no-data", 38);
        }

        if (rf & 2) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("title", "No data to display");
        }
      }

      var TableComponent = /*#__PURE__*/function () {
        function TableComponent(pagerService) {
          _classCallCheck(this, TableComponent);

          this.pagerService = pagerService;
          this.pager = {};
          this.pagerPages = new Array();
          this.pageSizeOptions = [];
          this.pageSize = 5;
          this.action = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.actions = [{
            type: 'edit',
            iconClass: 'fa-edit',
            title: 'Edit'
          }, {
            type: 'delete',
            iconClass: 'fa-trash-alt',
            title: 'Delete'
          }, {
            type: 'chart',
            iconClass: 'fa-chart',
            title: 'Chart of the log'
          }, {
            type: 'reference',
            iconClass: 'fa-cloudscale',
            title: 'Calculate reference curve'
          }, {
            type: 'start',
            iconClass: 'fa-play',
            title: 'Start monitoring'
          }];
        }

        _createClass(TableComponent, [{
          key: "onAction",
          value: function onAction(type, item) {
            if (item.cell_type == 'action') return;

            if (type === 'sort') {
              this.columns.forEach(function (column) {
                column.sort_by = false;
              });
              item.sort_by = true;
              var property = item.property_name;
              var nested_property = item.nested_property;
              var sortedRows = this.items;

              switch (item.cell_type) {
                case 'text':
                case 'status':
                  {
                    if (isNaN(item[property])) {
                      this.items.sort(function (a, b) {
                        return 0 - (a[property] > b[property] ? 1 : -1);
                      });
                    } else {
                      this.items.sort(function (a, b) {
                        return a[property].localeCompare(b[property]);
                      });
                    }

                    break;
                  }

                case 'nested-text':
                  {
                    this.items.sort(function (a, b) {
                      return a[property][nested_property].localeCompare(b[property][nested_property]);
                    });
                    break;
                  }

                case 'date':
                  {
                    this.items.sort(function (a, b) {
                      return new Date(b[property]).getTime() - new Date(a[property]).getTime();
                    });
                    break;
                  }

                default:
                  {
                    return;
                  }
              }

              item.descending = !item.descending;
              if (!item.descending) sortedRows.reverse();
            } else {
              this.action.emit({
                type: type,
                item: item
              });
            }
          } // set current page for paging

        }, {
          key: "setPage",
          value: function setPage(page, pageSize) {
            if (page < 1 || page > this.pager.totalPages) {
              return;
            } // get pager object from service


            this.pager = this.pagerService.getPager(this.allItems.length, page, pageSize);

            if (this.pager.currentPage > this.pager.totalPages) {
              this.pager.currentPage = 1;
              this.pagedItems = _toConsumableArray(this.allItems);
            } else {
              // get current page of items
              this.pagedItems = this.allItems.slice(this.pager.startIndex, this.pager.endIndex + 1);
            } // pass data into table


            this.items = _toConsumableArray(this.pagedItems); // sort by most recent

            this.items.sort(function (a, b) {
              return new Date(b['createdOn']).getTime() - new Date(a['createdOn']).getTime();
            });
          } // change data for table when page size is changed

        }, {
          key: "changePageSize",
          value: function changePageSize(pageSize) {
            this.pageSize = Number(pageSize);
            this.setPage(this.pager.currentPage, Number(pageSize));
            this.pagerPages = _toConsumableArray(this.preparePages());
          } // generate data for pager

        }, {
          key: "preparePages",
          value: function preparePages() {
            var res = [];

            for (var i = 1; i <= this.pager.totalPages; i++) {
              res.push(i);
            }

            return res;
          } // generate data for page size selecotr

        }, {
          key: "preparePagerOptions",
          value: function preparePagerOptions() {
            var maxItem = this.pager.totalPages * this.pageSize;
            var res = [];

            for (var i = this.pageSize; i <= maxItem; i += this.pageSize) {
              res.push(i);
            }

            return res;
          } // make first letter of word capital

        }, {
          key: "capitalizeFirst",
          value: function capitalizeFirst(string) {
            string = string.toLowerCase();
            return string.charAt(0).toUpperCase() + string.slice(1);
          }
        }, {
          key: "data",
          set: function set(data) {
            (data === null || data === void 0 ? void 0 : data.length) ? (this.pageSize = 5, this.allItems = _toConsumableArray(data), this.setPage(1, this.pageSize), this.pagerPages = _toConsumableArray(this.preparePages()), this.pageSizeOptions = _toConsumableArray(this.preparePagerOptions())) : (this.items = [], this.allItems = [], this.pagerPages = [], this.pageSizeOptions = []);
          }
        }]);

        return TableComponent;
      }();

      TableComponent.ɵfac = function TableComponent_Factory(t) {
        return new (t || TableComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_pager_service__WEBPACK_IMPORTED_MODULE_1__["PagerService"]));
      };

      TableComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: TableComponent,
        selectors: [["app-table"]],
        inputs: {
          data: "data",
          columns: "columns"
        },
        outputs: {
          action: "action"
        },
        decls: 3,
        vars: 2,
        consts: [["class", "table-auto w-full mb-5 overflow-x-auto", 4, "ngIf", "ngIfElse"], ["noData", ""], [1, "table-auto", "w-full", "mb-5", "overflow-x-auto"], [4, "ngFor", "ngForOf"], [4, "ngIf"], [1, "p-2", "whitespace-no-wrap", 3, "ngClass", "click"], ["class", "fas fa-caret-down ml-2 text-gray-500 absolute mt-1", "style", "z-index: -1", 3, "ngClass", 4, "ngIf"], [1, "fas", "fa-caret-down", "ml-2", "text-gray-500", "absolute", "mt-1", 2, "z-index", "-1", 3, "ngClass"], [1, "border-t", "border-b", "hover:bg-gray-200", 3, "ngClass"], [1, "text-center", "p-2", "text-sm", 3, "ngSwitch"], [4, "ngSwitchCase"], [4, "ngSwitchDefault"], [1, "whitespace-no-wrap", 3, "ngClass"], [1, "fas", "fa-circle", "text-xs", 3, "title"], [1, "flex", "justify-center"], [1, "inline-flex", "items-center", "cursor-not-allowed"], ["type", "checkbox", "formControlName", "automaticMonitoring", 1, "form-checkbox", "h-5", "w-5", "cursor-not-allowed", 2, "color", "#2d3748", 3, "checked"], [1, "whitespace-no-wrap"], ["class", "opacity-0 py-2 px-4 mx-1 rounded-md inline-flex items-center duration-300 whitespace-no-wrap outline-none focus:outline-none text-sm border border-transparent text-white font-normal shadow-none", 4, "ngIf"], ["class", "mx-1", 3, "text", "icon", "type", "customTitle", "click", 4, "ngIf", "ngIfElse"], ["class", "mx-1", 3, "text", "icon", "type", "customTitle", "click", 4, "ngIf"], ["class", "mx-1", 3, "text", "icon", "type", "customTitle", 4, "ngIf", "ngIfElse"], ["noReference", ""], ["noRefType", ""], ["class", "inline-flex items-center cursor-pointer align-middle", 4, "ngIf"], [1, "opacity-0", "py-2", "px-4", "mx-1", "rounded-md", "inline-flex", "items-center", "duration-300", "whitespace-no-wrap", "outline-none", "focus:outline-none", "text-sm", "border", "border-transparent", "text-white", "font-normal", "shadow-none"], [1, "fas", "fa-play"], [1, "mx-1", 3, "text", "icon", "type", "customTitle", "click"], [1, "mx-1", 3, "text", "icon", "type", "customTitle"], [1, "inline-flex", "items-center", "cursor-pointer", "align-middle"], ["type", "checkbox", 1, "form-checkbox", "h-5", "w-5", "cursor-pointer", 2, "color", "#2d3748", 3, "checked", "click"], [1, "font-bold", 3, "ngClass"], [1, "text-md", "font-thin", "text-black"], [1, "text-center"], ["class", "cursor-pointer hover:text-black hover:font-semibold pl-1", 3, "ngClass", "click", 4, "ngFor", "ngForOf"], [1, "h-auto", "text-black", "text-center"], [1, "w-1/2", "text-lg", 3, "text", "items", "selected", "width", "select"], [1, "cursor-pointer", "hover:text-black", "hover:font-semibold", "pl-1", 3, "ngClass", "click"], [3, "title"]],
        template: function TableComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, TableComponent_table_0_Template, 7, 3, "table", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, TableComponent_ng_template_1_Template, 1, 1, "ng-template", null, 1, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);
          }

          if (rf & 2) {
            var _r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.items.length && ctx.columns)("ngIfElse", _r1);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgSwitch"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgSwitchCase"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgSwitchDefault"], _button_button_component__WEBPACK_IMPORTED_MODULE_3__["ButtonComponent"], _dropdown_dropdown_component__WEBPACK_IMPORTED_MODULE_4__["DropdownComponent"], _no_data_no_data_component__WEBPACK_IMPORTED_MODULE_5__["NoDataComponent"]],
        pipes: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["DatePipe"]],
        styles: ["tbody[_ngcontent-%COMP%]    > tr[_ngcontent-%COMP%] {\n        box-shadow: inset -1px 0px 0px 0px #e2e8f0;\n      }", "input[type=\"checkbox\"][_ngcontent-%COMP%]{box-shadow: none !important; outline: none !important;}"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](TableComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-table',
            templateUrl: './table.component.html',
            styles: ["\n      tbody > tr {\n        box-shadow: inset -1px 0px 0px 0px #e2e8f0;\n      }\n    ", 'input[type="checkbox"]{box-shadow: none !important; outline: none !important;}']
          }]
        }], function () {
          return [{
            type: _services_pager_service__WEBPACK_IMPORTED_MODULE_1__["PagerService"]
          }];
        }, {
          data: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          columns: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          action: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }]
        });
      })();
      /***/

    },

    /***/
    "ZAI4":
    /*!*******************************!*\
      !*** ./src/app/app.module.ts ***!
      \*******************************/

    /*! exports provided: AppModule */

    /***/
    function ZAI4(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "AppModule", function () {
        return AppModule;
      });
      /* harmony import */


      var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/platform-browser */
      "jhN1");
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _app_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./app-routing.module */
      "vY5A");
      /* harmony import */


      var _app_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./app.component */
      "Sy1n");
      /* harmony import */


      var src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! src/app/shared/shared.module */
      "PCNd");
      /* harmony import */


      var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! @angular/common/http */
      "tk/3");
      /* harmony import */


      var _ngrx_store__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! @ngrx/store */
      "l7P3");
      /* harmony import */


      var _ngrx_effects__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(
      /*! @ngrx/effects */
      "9jGm");
      /* harmony import */


      var _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(
      /*! @ngrx/store-devtools */
      "agSv");
      /* harmony import */


      var _effects__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(
      /*! @effects */
      "RxZS");
      /* harmony import */


      var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(
      /*! @angular/platform-browser/animations */
      "R1ws");
      /* harmony import */


      var _layout_layout_module__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(
      /*! ./layout/layout.module */
      "Tx//");
      /* harmony import */


      var _store_reducers__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(
      /*! ./store/reducers */
      "tg95");
      /* harmony import */


      var _actions__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(
      /*! @actions */
      "v8Ou");
      /* harmony import */


      var _shared_interceptors_busy_indicator_interceptor__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(
      /*! ./shared/interceptors/busy-indicator.interceptor */
      "jCGl");

      var AppModule = function AppModule() {
        _classCallCheck(this, AppModule);
      };

      AppModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineNgModule"]({
        type: AppModule,
        bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]]
      });
      AppModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjector"]({
        factory: function AppModule_Factory(t) {
          return new (t || AppModule)();
        },
        providers: [{
          provide: _angular_core__WEBPACK_IMPORTED_MODULE_1__["APP_INITIALIZER"],
          useFactory: function useFactory(store) {
            return function () {
              store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_13__["LoadTools"]());
              store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_13__["LoadPlcs"]());
            };
          },
          multi: true,
          deps: [_ngrx_store__WEBPACK_IMPORTED_MODULE_6__["Store"]]
        }, {
          provide: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HTTP_INTERCEPTORS"],
          useClass: _shared_interceptors_busy_indicator_interceptor__WEBPACK_IMPORTED_MODULE_14__["BusyIndicatorInterceptor"],
          multi: true
        }],
        imports: [[_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"], _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_10__["BrowserAnimationsModule"], _app_routing_module__WEBPACK_IMPORTED_MODULE_2__["AppRoutingModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _layout_layout_module__WEBPACK_IMPORTED_MODULE_11__["LayoutModule"], _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClientModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_6__["StoreModule"].forRoot(_store_reducers__WEBPACK_IMPORTED_MODULE_12__["reducers"]), _ngrx_effects__WEBPACK_IMPORTED_MODULE_7__["EffectsModule"].forRoot([_effects__WEBPACK_IMPORTED_MODULE_9__["PlcEffect"], _effects__WEBPACK_IMPORTED_MODULE_9__["ToolEffects"]]), _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_8__["StoreDevtoolsModule"].instrument({
          maxAge: 25
        })]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsetNgModuleScope"](AppModule, {
          declarations: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]],
          imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"], _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_10__["BrowserAnimationsModule"], _app_routing_module__WEBPACK_IMPORTED_MODULE_2__["AppRoutingModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _layout_layout_module__WEBPACK_IMPORTED_MODULE_11__["LayoutModule"], _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClientModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_6__["StoreRootModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_7__["EffectsRootModule"], _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_8__["StoreDevtoolsModule"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](AppModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"],
          args: [{
            declarations: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]],
            imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"], _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_10__["BrowserAnimationsModule"], _app_routing_module__WEBPACK_IMPORTED_MODULE_2__["AppRoutingModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _layout_layout_module__WEBPACK_IMPORTED_MODULE_11__["LayoutModule"], _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClientModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_6__["StoreModule"].forRoot(_store_reducers__WEBPACK_IMPORTED_MODULE_12__["reducers"]), _ngrx_effects__WEBPACK_IMPORTED_MODULE_7__["EffectsModule"].forRoot([_effects__WEBPACK_IMPORTED_MODULE_9__["PlcEffect"], _effects__WEBPACK_IMPORTED_MODULE_9__["ToolEffects"]]), _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_8__["StoreDevtoolsModule"].instrument({
              maxAge: 25
            })],
            providers: [{
              provide: _angular_core__WEBPACK_IMPORTED_MODULE_1__["APP_INITIALIZER"],
              useFactory: function useFactory(store) {
                return function () {
                  store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_13__["LoadTools"]());
                  store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_13__["LoadPlcs"]());
                };
              },
              multi: true,
              deps: [_ngrx_store__WEBPACK_IMPORTED_MODULE_6__["Store"]]
            }, {
              provide: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HTTP_INTERCEPTORS"],
              useClass: _shared_interceptors_busy_indicator_interceptor__WEBPACK_IMPORTED_MODULE_14__["BusyIndicatorInterceptor"],
              multi: true
            }],
            // providers: [
            //   { provide: HTTP_INTERCEPTORS, useClass: NotificationInterceptorInterceptor, multi: true },
            // ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_3__["AppComponent"]]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "a4eG":
    /*!************************************************************!*\
      !*** ./src/app/shared/components/pager/pager.component.ts ***!
      \************************************************************/

    /*! exports provided: PagerComponent */

    /***/
    function a4eG(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PagerComponent", function () {
        return PagerComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _services_pager_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ../../services/pager.service */
      "PBnp");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      function PagerComponent_div_0_li_3_Template(rf, ctx) {
        if (rf & 1) {
          var _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "li", 8);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "a", 9);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function PagerComponent_div_0_li_3_Template_a_click_1_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r6);

            var page_r4 = ctx.$implicit;

            var ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            return ctx_r5.setPage(page_r4, ctx_r5.pageSize);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var page_r4 = ctx.$implicit;

          var ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", page_r4 === ctx_r1.pager.currentPage && "is-current");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](page_r4);
        }
      }

      function PagerComponent_div_0_option_7_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "option", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var option_r7 = ctx.$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("value", option_r7);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](option_r7);
        }
      }

      function PagerComponent_div_0_Template(rf, ctx) {
        if (rf & 1) {
          var _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "ul");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, PagerComponent_div_0_li_3_Template, 3, 2, "li", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "div", 4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "select", 5, 6);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("change", function PagerComponent_div_0_Template_select_change_5_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r9);

            var _r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](6);

            var ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            return ctx_r8.changePageSize(_r2.value);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, PagerComponent_div_0_option_7_Template, 2, 2, "option", 7);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.pagerPages);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.pageSizeOptions);
        }
      }

      var PagerComponent = /*#__PURE__*/function () {
        function PagerComponent(pagerService) {
          _classCallCheck(this, PagerComponent);

          this.pagerService = pagerService;
          this.filteredData = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.pager = {};
          this.pagerPages = new Array();
          this.pageSizeOptions = [];
          this.pageSize = 10;
          this.returnData = [];
        }

        _createClass(PagerComponent, [{
          key: "setPage",
          value: function setPage(page, pageSize) {
            if (page < 1 || page > this.pager.totalPages) {
              return;
            } // get pager object from service


            this.pager = this.pagerService.getPager(this.allItems.length, page, pageSize);

            if (this.pager.currentPage > this.pager.totalPages) {
              this.pager.currentPage = 1;
              this.pagedItems = _toConsumableArray(this.allItems);
            } else {
              // get current page of items
              this.pagedItems = this.allItems.slice(this.pager.startIndex, this.pager.endIndex + 1);
            } // pass data into table


            this.returnData = _toConsumableArray(this.pagedItems);
            this.filteredData.emit(this.returnData);
          }
        }, {
          key: "changePageSize",
          value: function changePageSize(pageSize) {
            this.pageSize = Number(pageSize);
            this.setPage(this.pager.currentPage, Number(pageSize));
            this.pagerPages = _toConsumableArray(this.preparePages());
          }
        }, {
          key: "preparePages",
          value: function preparePages() {
            var res = [];

            for (var i = 1; i <= this.pager.totalPages; i++) {
              res.push(i);
            }

            return res;
          }
        }, {
          key: "preparePagerOptions",
          value: function preparePagerOptions() {
            var maxItem = this.pager.totalPages * this.pageSize;
            var res = [];

            for (var i = this.pageSize; i <= maxItem; i += this.pageSize) {
              res.push(i);
            }

            return res;
          }
        }, {
          key: "data",
          set: function set(data) {
            if (data) {
              this.allItems = _toConsumableArray(data);
              this.setPage(1, this.pageSize);
              this.pagerPages = _toConsumableArray(this.preparePages());
              this.pageSizeOptions = _toConsumableArray(this.preparePagerOptions());
            }
          }
        }]);

        return PagerComponent;
      }();

      PagerComponent.ɵfac = function PagerComponent_Factory(t) {
        return new (t || PagerComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_pager_service__WEBPACK_IMPORTED_MODULE_1__["PagerService"]));
      };

      PagerComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: PagerComponent,
        selectors: [["app-pager"]],
        inputs: {
          data: "data"
        },
        outputs: {
          filteredData: "filteredData"
        },
        decls: 1,
        vars: 1,
        consts: [["class", "wrapper", 4, "ngIf"], [1, "wrapper"], [1, "pager"], ["class", "pager__page", 3, "ngClass", 4, "ngFor", "ngForOf"], [1, "selectWrapper"], [1, "inputGroup__select", 3, "change"], ["pageSizeSelector", ""], [3, "value", 4, "ngFor", "ngForOf"], [1, "pager__page", 3, "ngClass"], [3, "click"], [3, "value"]],
        template: function PagerComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, PagerComponent_div_0_Template, 8, 2, "div", 0);
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.pager.totalPages > 1);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgClass"]],
        styles: [".selectWrapper[_ngcontent-%COMP%] {\n  width: 5rem;\n}\n\n.wrapper[_ngcontent-%COMP%] {\n  width: 100%;\n  display: flex;\n  justify-content: space-between;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvc2hhcmVkL2NvbXBvbmVudHMvcGFnZXIvcGFnZXIuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxXQUFBO0FBQ0Y7O0FBRUE7RUFDRSxXQUFBO0VBQ0EsYUFBQTtFQUNBLDhCQUFBO0FBQ0YiLCJmaWxlIjoic3JjL2FwcC9zaGFyZWQvY29tcG9uZW50cy9wYWdlci9wYWdlci5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5zZWxlY3RXcmFwcGVyIHtcbiAgd2lkdGg6IDVyZW07XG59XG5cbi53cmFwcGVyIHtcbiAgd2lkdGg6IDEwMCU7XG4gIGRpc3BsYXk6IGZsZXg7XG4gIGp1c3RpZnktY29udGVudDogc3BhY2UtYmV0d2Vlbjtcbn1cbiJdfQ== */"],
        changeDetection: 0
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PagerComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-pager',
            changeDetection: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ChangeDetectionStrategy"].OnPush,
            templateUrl: './pager.component.html',
            styleUrls: ['./pager.component.scss']
          }]
        }], function () {
          return [{
            type: _services_pager_service__WEBPACK_IMPORTED_MODULE_1__["PagerService"]
          }];
        }, {
          data: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          filteredData: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }]
        });
      })();
      /***/

    },

    /***/
    "eSv6":
    /*!****************************************************************!*\
      !*** ./src/app/layout/components/sidebar/sidebar.component.ts ***!
      \****************************************************************/

    /*! exports provided: SidebarComponent */

    /***/
    function eSv6(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "SidebarComponent", function () {
        return SidebarComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");

      function SidebarComponent_a_2_span_3_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "span");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var item_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](item_r2.name);
        }
      }

      var _c0 = function _c0() {
        return ["text-gray-800", "font-semibold", "bg-gray-100", "selected"];
      };

      function SidebarComponent_a_2_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "a", 6);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "span", 7);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "i", 8);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, SidebarComponent_a_2_span_3_Template, 2, 1, "span", 5);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var item_r2 = ctx.$implicit;

          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("routerLink", item_r2.link)("routerLinkActive", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction0"](5, _c0))("title", item_r2.title);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", item_r2.iconClass);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx_r0.collapsed);
        }
      }

      function SidebarComponent_span_6_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "span");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Collapse");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }
      }

      var _c1 = function _c1(a0) {
        return {
          "w-40": a0
        };
      };

      var _c2 = function _c2(a0, a1) {
        return {
          "fa-angle-double-left": a0,
          "fa-angle-double-right": a1
        };
      };

      var SidebarComponent = /*#__PURE__*/function () {
        function SidebarComponent() {
          _classCallCheck(this, SidebarComponent);

          this.items = [{
            name: 'PLCs',
            iconClass: 'fa-robot',
            title: 'PLCs',
            link: 'plc'
          }, {
            name: 'Tools',
            iconClass: 'fa-toolbox',
            title: 'Tools',
            link: 'tool'
          }, {
            name: 'Viewer',
            iconClass: 'fa-chart-bar',
            title: 'Viewer',
            link: 'viewer'
          }];
          this.collapsed = sessionStorage.getItem('SIDEBAR_STATE') == 'true';
        }

        _createClass(SidebarComponent, [{
          key: "collapseSidebar",
          value: function collapseSidebar() {
            this.collapsed = !this.collapsed;
            sessionStorage.setItem('SIDEBAR_STATE', this.collapsed.toString());
          }
        }]);

        return SidebarComponent;
      }();

      SidebarComponent.ɵfac = function SidebarComponent_Factory(t) {
        return new (t || SidebarComponent)();
      };

      SidebarComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: SidebarComponent,
        selectors: [["app-sidebar"]],
        decls: 7,
        vars: 9,
        consts: [[1, "flex", "flex-col", "justify-between", "w-12", "h-full", "pt-6", "text-gray-600", "bg-gray-300", "border-r", "border-gray-400", "transition-all", "duration-150", 3, "ngClass"], ["class", "flex items-center flex-no-wrap w-full px-4 py-3 text-sm transition duration-150 hover:text-gray-800 hover:bg-gray-100", 3, "routerLink", "routerLinkActive", "title", 4, "ngFor", "ngForOf"], [1, "border-t", "border-gray-400", "px-4", "py-3", "hover:bg-gray-100", "hover:text-gray-800", "cursor-pointer", "transition", "duration-150", 3, "click"], [1, "text-sm", "select-none", "truncate"], [1, "fas", "mr-2", 3, "ngClass"], [4, "ngIf"], [1, "flex", "items-center", "flex-no-wrap", "w-full", "px-4", "py-3", "text-sm", "transition", "duration-150", "hover:text-gray-800", "hover:bg-gray-100", 3, "routerLink", "routerLinkActive", "title"], [1, "truncate"], [1, "fas", "mr-3", 3, "ngClass"]],
        template: function SidebarComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "nav", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, SidebarComponent_a_2_Template, 4, 6, "a", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function SidebarComponent_Template_div_click_3_listener() {
              return ctx.collapseSidebar();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "span", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](5, "i", 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](6, SidebarComponent_span_6_Template, 2, 0, "span", 5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction1"](4, _c1, !ctx.collapsed));

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx.items);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](6, _c2, !ctx.collapsed, ctx.collapsed));

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.collapsed);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgIf"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterLinkWithHref"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterLinkActive"]],
        styles: [".selected[_ngcontent-%COMP%] {\n        box-shadow: inset 3px 0px 0px 0px rgba(45, 55, 72, 1);\n      }"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](SidebarComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-sidebar',
            template: "\n    <nav\n      class=\"flex flex-col justify-between w-12 h-full pt-6 text-gray-600 bg-gray-300 border-r border-gray-400 transition-all duration-150\"\n      [ngClass]=\"{ 'w-40': !collapsed }\"\n    >\n      <!-- Sidebar items -->\n      <div>\n        <a\n          *ngFor=\"let item of items\"\n          [routerLink]=\"item.link\"\n          [routerLinkActive]=\"['text-gray-800', 'font-semibold', 'bg-gray-100', 'selected']\"\n          [title]=\"item.title\"\n          class=\"flex items-center flex-no-wrap w-full px-4 py-3 text-sm transition duration-150 hover:text-gray-800 hover:bg-gray-100\"\n        >\n          <span class=\"truncate\">\n            <i class=\"fas mr-3\" [ngClass]=\"item.iconClass\"></i>\n            <span *ngIf=\"!collapsed\">{{ item.name }}</span>\n          </span>\n        </a>\n      </div>\n\n      <!-- Collapse sidebar -->\n      <div\n        class=\"border-t border-gray-400 px-4 py-3 hover:bg-gray-100 hover:text-gray-800 cursor-pointer transition duration-150\"\n        (click)=\"collapseSidebar()\"\n      >\n        <span class=\"text-sm select-none truncate\">\n          <i\n            class=\"fas mr-2\"\n            [ngClass]=\"{ 'fa-angle-double-left': !collapsed, 'fa-angle-double-right': collapsed }\"\n          ></i>\n          <span *ngIf=\"!collapsed\">Collapse</span>\n        </span>\n      </div>\n    </nav>\n  ",
            styles: ["\n      .selected {\n        box-shadow: inset 3px 0px 0px 0px rgba(45, 55, 72, 1);\n      }\n    "]
          }]
        }], function () {
          return [];
        }, null);
      })();
      /***/

    },

    /***/
    "fk77":
    /*!*************************************************!*\
      !*** ./src/app/shared/animations/animations.ts ***!
      \*************************************************/

    /*! exports provided: fadeInFadeOut, toggle */

    /***/
    function fk77(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "fadeInFadeOut", function () {
        return fadeInFadeOut;
      });
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "toggle", function () {
        return toggle;
      });
      /* harmony import */


      var _angular_animations__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/animations */
      "R0Ic");

      var fadeInFadeOut = Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["trigger"])('fadeInFadeOut', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["transition"])(':enter', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 0
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])(300, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 1
      }))]), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["transition"])(':leave', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])(300, Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 0
      }))])]);
      var toggle = Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["trigger"])('toggle', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["state"])('opened', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 1,
        transform: 'scale(1)'
      })), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["state"])('closed', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 0,
        transform: 'scale(0.95)'
      })), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["transition"])('*<=>*', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])('100ms'))]);
      /***/
    },

    /***/
    "gn+R":
    /*!*******************************************************!*\
      !*** ./src/app/shared/animations/route-animations.ts ***!
      \*******************************************************/

    /*! exports provided: fader */

    /***/
    function gnR(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "fader", function () {
        return fader;
      });
      /* harmony import */


      var _angular_animations__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/animations */
      "R0Ic");

      var fader = Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["trigger"])('routeAnimations', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["transition"])('* => *', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        position: 'relative'
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter, :leave', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        position: 'absolute',
        top: 0,
        left: 0,
        width: '100%'
      })], {
        optional: true
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        left: '100%',
        opacity: 0
      })], {
        optional: true
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':leave', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animateChild"])(), {
        optional: true
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["group"])([Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':leave', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])('200ms ease-out', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        opacity: 0
      }))], {
        optional: true
      }), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter', [Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])('300ms ease-out', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({
        left: '0%',
        opacity: 1
      }))], {
        optional: true
      })]), Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animateChild"])(), {
        optional: true
      })])]);
      /***/
    },

    /***/
    "h3XU":
    /*!******************************************************!*\
      !*** ./src/app/shared/modules/modal/modal.module.ts ***!
      \******************************************************/

    /*! exports provided: ModalModule */

    /***/
    function h3XU(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ModalModule", function () {
        return ModalModule;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _components_delete_delete_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./components/delete/delete.component */
      "zfld");
      /* harmony import */


      var _container_modal_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./container/modal.component */
      "VRmN");

      var ModalModule = function ModalModule() {
        _classCallCheck(this, ModalModule);
      };

      ModalModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({
        type: ModalModule
      });
      ModalModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({
        factory: function ModalModule_Factory(t) {
          return new (t || ModalModule)();
        },
        imports: [[_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](ModalModule, {
          declarations: [_container_modal_component__WEBPACK_IMPORTED_MODULE_3__["ModalComponent"], _components_delete_delete_component__WEBPACK_IMPORTED_MODULE_2__["DeleteComponent"]],
          imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]],
          exports: [_container_modal_component__WEBPACK_IMPORTED_MODULE_3__["ModalComponent"], _components_delete_delete_component__WEBPACK_IMPORTED_MODULE_2__["DeleteComponent"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ModalModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
          args: [{
            declarations: [_container_modal_component__WEBPACK_IMPORTED_MODULE_3__["ModalComponent"], _components_delete_delete_component__WEBPACK_IMPORTED_MODULE_2__["DeleteComponent"]],
            imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]],
            exports: [_container_modal_component__WEBPACK_IMPORTED_MODULE_3__["ModalComponent"], _components_delete_delete_component__WEBPACK_IMPORTED_MODULE_2__["DeleteComponent"]]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "jCGl":
    /*!*******************************************************************!*\
      !*** ./src/app/shared/interceptors/busy-indicator.interceptor.ts ***!
      \*******************************************************************/

    /*! exports provided: BusyIndicatorInterceptor */

    /***/
    function jCGl(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "BusyIndicatorInterceptor", function () {
        return BusyIndicatorInterceptor;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ../services/busy-indicator.service */
      "l1xV");
      /* harmony import */


      var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! rxjs/operators */
      "kU1M");

      var BusyIndicatorInterceptor = /*#__PURE__*/function () {
        function BusyIndicatorInterceptor(busy) {
          _classCallCheck(this, BusyIndicatorInterceptor);

          this.busy = busy;
        }

        _createClass(BusyIndicatorInterceptor, [{
          key: "intercept",
          value: function intercept(request, next) {
            var _this4 = this;

            this.busy.show();
            return next.handle(request).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["finalize"])(function () {
              _this4.busy.hide();
            }));
          }
        }]);

        return BusyIndicatorInterceptor;
      }();

      BusyIndicatorInterceptor.ɵfac = function BusyIndicatorInterceptor_Factory(t) {
        return new (t || BusyIndicatorInterceptor)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_1__["BusyIndicatorService"]));
      };

      BusyIndicatorInterceptor.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: BusyIndicatorInterceptor,
        factory: BusyIndicatorInterceptor.ɵfac
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](BusyIndicatorInterceptor, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"]
        }], function () {
          return [{
            type: _services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_1__["BusyIndicatorService"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "jYWo":
    /*!**************************************************************!*\
      !*** ./src/app/shared/directives/click-outside.directive.ts ***!
      \**************************************************************/

    /*! exports provided: ClickOutsideDirective */

    /***/
    function jYWo(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ClickOutsideDirective", function () {
        return ClickOutsideDirective;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var ClickOutsideDirective = /*#__PURE__*/function () {
        function ClickOutsideDirective(_elRef) {
          _classCallCheck(this, ClickOutsideDirective);

          this._elRef = _elRef;
          this.clickOutside = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        }

        _createClass(ClickOutsideDirective, [{
          key: "onClick",
          value: function onClick(event) {
            var clickedInside = this._elRef.nativeElement.contains(event.target);

            if (!clickedInside) {
              this.clickOutside.emit(event);
            }
          }
        }]);

        return ClickOutsideDirective;
      }();

      ClickOutsideDirective.ɵfac = function ClickOutsideDirective_Factory(t) {
        return new (t || ClickOutsideDirective)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"]));
      };

      ClickOutsideDirective.ɵdir = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineDirective"]({
        type: ClickOutsideDirective,
        selectors: [["", "clickOutside", ""]],
        hostBindings: function ClickOutsideDirective_HostBindings(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ClickOutsideDirective_click_HostBindingHandler($event) {
              return ctx.onClick($event);
            }, false, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵresolveDocument"]);
          }
        },
        outputs: {
          clickOutside: "clickOutside"
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ClickOutsideDirective, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Directive"],
          args: [{
            selector: '[clickOutside]'
          }]
        }], function () {
          return [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"]
          }];
        }, {
          clickOutside: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }],
          onClick: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"],
            args: ['document:click', ['$event']]
          }]
        });
      })();
      /***/

    },

    /***/
    "k5Aa":
    /*!***************************************!*\
      !*** ./src/app/shared/enums/index.ts ***!
      \***************************************/

    /*! exports provided: StopReactionType, ToleranceEnum, WebsocketEnums */

    /***/
    function k5Aa(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _stop_reaction__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./stop-reaction */
      "+tET");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "StopReactionType", function () {
        return _stop_reaction__WEBPACK_IMPORTED_MODULE_0__["StopReactionType"];
      });
      /* harmony import */


      var _tolerance__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./tolerance */
      "FXeW");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "ToleranceEnum", function () {
        return _tolerance__WEBPACK_IMPORTED_MODULE_1__["ToleranceEnum"];
      });
      /* harmony import */


      var _websocket_enum__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./websocket-enum */
      "KHs+");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "WebsocketEnums", function () {
        return _websocket_enum__WEBPACK_IMPORTED_MODULE_2__["WebsocketEnums"];
      });
      /***/

    },

    /***/
    "l1xV":
    /*!***********************************************************!*\
      !*** ./src/app/shared/services/busy-indicator.service.ts ***!
      \***********************************************************/

    /*! exports provided: BusyIndicatorService */

    /***/
    function l1xV(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "BusyIndicatorService", function () {
        return BusyIndicatorService;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! rxjs */
      "qCKp");

      var BusyIndicatorService = /*#__PURE__*/function () {
        function BusyIndicatorService() {
          _classCallCheck(this, BusyIndicatorService);

          this.$isBusy = new rxjs__WEBPACK_IMPORTED_MODULE_1__["BehaviorSubject"](false);
        }

        _createClass(BusyIndicatorService, [{
          key: "show",
          value: function show() {
            this.$isBusy.next(true);
          }
        }, {
          key: "hide",
          value: function hide() {
            this.$isBusy.next(false);
          }
        }, {
          key: "subscribeToState",
          value: function subscribeToState() {
            return this.$isBusy.asObservable();
          }
        }]);

        return BusyIndicatorService;
      }();

      BusyIndicatorService.ɵfac = function BusyIndicatorService_Factory(t) {
        return new (t || BusyIndicatorService)();
      };

      BusyIndicatorService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: BusyIndicatorService,
        factory: BusyIndicatorService.ɵfac,
        providedIn: 'root'
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](BusyIndicatorService, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
          args: [{
            providedIn: 'root'
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "lOC7":
    /*!******************************************************************!*\
      !*** ./src/app/shared/components/plc-list/plc-list.component.ts ***!
      \******************************************************************/

    /*! exports provided: PlcListComponent */

    /***/
    function lOC7(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcListComponent", function () {
        return PlcListComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      function PlcListComponent_ng_container_1_a_1_Template(rf, ctx) {
        if (rf & 1) {
          var _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "a", 4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function PlcListComponent_ng_container_1_a_1_Template_a_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r6);

            var item_r4 = ctx.$implicit;

            var ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

            ctx_r5.selectedEntity = item_r4;
            return ctx_r5.selected.emit(item_r4);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var item_r4 = ctx.$implicit;

          var ctx_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", (ctx_r3.selectedEntity == null ? null : ctx_r3.selectedEntity.id) === item_r4.id && "text-gray-800 font-semibold bg-gray-100 selected");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", item_r4.name, " ");
        }
      }

      function PlcListComponent_ng_container_1_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerStart"](0);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, PlcListComponent_ng_container_1_a_1_Template, 2, 2, "a", 3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementContainerEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r0.listPlcs);
        }
      }

      var _c0 = function _c0(a0, a1) {
        return {
          "text-gray-800 font-semibold bg-gray-100 selected": a0,
          "bg-gray-400 font-semibold": a1
        };
      };

      function PlcListComponent_ng_template_2_div_0_div_5_a_1_Template(rf, ctx) {
        if (rf & 1) {
          var _r13 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "a", 12);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function PlcListComponent_ng_template_2_div_0_div_5_a_1_Template_a_click_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r13);

            var tool_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

            var ctx_r12 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3);

            ctx_r12.selectedEntity = tool_r10;
            return ctx_r12.selected.emit(tool_r10);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var tool_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          var ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](2, _c0, (ctx_r11.selectedEntity == null ? null : ctx_r11.selectedEntity.id) === tool_r10.id, tool_r10.isActive));

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](tool_r10.name);
        }
      }

      function PlcListComponent_ng_template_2_div_0_div_5_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, PlcListComponent_ng_template_2_div_0_div_5_a_1_Template, 2, 5, "a", 11);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var tool_r10 = ctx.$implicit;

          var item_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", tool_r10.plcId === item_r8.id);
        }
      }

      function PlcListComponent_ng_template_2_div_0_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 6);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 7);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "i", 8);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "span", 9);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, PlcListComponent_ng_template_2_div_0_div_5_Template, 2, 1, "div", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var item_r8 = ctx.$implicit;

          var ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](item_r8.name);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r7.listTools);
        }
      }

      function PlcListComponent_ng_template_2_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, PlcListComponent_ng_template_2_div_0_Template, 6, 2, "div", 5);
        }

        if (rf & 2) {
          var ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx_r2.listPlcs);
        }
      }

      var PlcListComponent = /*#__PURE__*/function () {
        function PlcListComponent() {
          _classCallCheck(this, PlcListComponent);

          this.tools = false;
          this.selected = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        }

        _createClass(PlcListComponent, [{
          key: "list",
          set: function set(list) {
            (list === null || list === void 0 ? void 0 : list.length) ? this.listPlcs = _toConsumableArray(list) : this.listPlcs = [];
          }
        }, {
          key: "toolsList",
          set: function set(tools) {
            (tools === null || tools === void 0 ? void 0 : tools.length) ? this.listTools = _toConsumableArray(tools) : this.listTools = [];
          }
        }]);

        return PlcListComponent;
      }();

      PlcListComponent.ɵfac = function PlcListComponent_Factory(t) {
        return new (t || PlcListComponent)();
      };

      PlcListComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: PlcListComponent,
        selectors: [["app-plc-list"]],
        inputs: {
          tools: "tools",
          list: "list",
          toolsList: "toolsList"
        },
        outputs: {
          selected: "selected"
        },
        decls: 4,
        vars: 2,
        consts: [[1, "h-full", "w-40", "bg-gray-300", "border-solid", "border-r", "border-gray-400", "py-6", "text-gray-600", "overflow-y-auto", "custom-scroll"], [4, "ngIf", "ngIfElse"], ["withTools", ""], ["class", "block w-full px-6 py-3 text-sm font-semibold hover:text-black transition-colors duration-500 cursor-pointer", 3, "ngClass", "click", 4, "ngFor", "ngForOf"], [1, "block", "w-full", "px-6", "py-3", "text-sm", "font-semibold", "hover:text-black", "transition-colors", "duration-500", "cursor-pointer", 3, "ngClass", "click"], ["class", "w-full h-auto", 4, "ngFor", "ngForOf"], [1, "w-full", "h-auto"], [1, "block", "w-full", "px-6", "py-3", "text-sm", "font-semibold"], [1, "fas", "fa-robot"], [1, "ml-2"], [4, "ngFor", "ngForOf"], ["class", "block w-full px-6 py-2 text-sm hover:text-black cursor-pointer transition-colors duration-500", 3, "ngClass", "click", 4, "ngIf"], [1, "block", "w-full", "px-6", "py-2", "text-sm", "hover:text-black", "cursor-pointer", "transition-colors", "duration-500", 3, "ngClass", "click"]],
        template: function PlcListComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "nav", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, PlcListComponent_ng_container_1_Template, 2, 1, "ng-container", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, PlcListComponent_ng_template_2_Template, 1, 1, "ng-template", null, 2, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            var _r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.tools)("ngIfElse", _r1);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_1__["NgClass"]],
        styles: [".selected[_ngcontent-%COMP%] {\n        box-shadow: inset 3px 0px 0px 0px rgba(45, 55, 72, 1);\n      }"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PlcListComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-plc-list',
            template: "<nav\n    class=\"h-full w-40 bg-gray-300 border-solid border-r border-gray-400 py-6 text-gray-600 overflow-y-auto custom-scroll\"\n  >\n    <ng-container *ngIf=\"!tools; else withTools\">\n      <a\n        *ngFor=\"let item of listPlcs\"\n        class=\"block w-full px-6 py-3 text-sm font-semibold hover:text-black transition-colors duration-500 cursor-pointer\"\n        [ngClass]=\"\n          selectedEntity?.id === item.id && 'text-gray-800 font-semibold bg-gray-100 selected'\n        \"\n        (click)=\"selectedEntity = item; selected.emit(item)\"\n      >\n        {{ item.name }}\n      </a>\n    </ng-container>\n    <ng-template #withTools>\n      <div *ngFor=\"let item of listPlcs\" class=\"w-full h-auto\">\n        <div class=\"block w-full px-6 py-3 text-sm font-semibold\">\n          <i class=\"fas fa-robot\"></i>\n          <span class=\"ml-2\">{{ item.name }}</span>\n        </div>\n        <div *ngFor=\"let tool of listTools\">\n          <a\n            *ngIf=\"tool.plcId === item.id\"\n            class=\"block w-full px-6 py-2 text-sm hover:text-black cursor-pointer transition-colors duration-500\"\n            [ngClass]=\"{\n              'text-gray-800 font-semibold bg-gray-100 selected': selectedEntity?.id === tool.id,\n              'bg-gray-400 font-semibold': tool.isActive\n            }\"\n            (click)=\"selectedEntity = tool; selected.emit(tool)\"\n            >{{ tool.name }}</a\n          >\n        </div>\n      </div>\n    </ng-template>\n  </nav>",
            styles: ["\n      .selected {\n        box-shadow: inset 3px 0px 0px 0px rgba(45, 55, 72, 1);\n      }\n    "]
          }]
        }], null, {
          tools: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          list: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          toolsList: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          selected: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }]
        });
      })();
      /***/

    },

    /***/
    "psMM":
    /*!***********************************************!*\
      !*** ./src/app/store/reducers/plc.reducer.ts ***!
      \***********************************************/

    /*! exports provided: PlcReducer */

    /***/
    function psMM(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcReducer", function () {
        return PlcReducer;
      });
      /* harmony import */


      var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @actions */
      "v8Ou");

      var initialState = {
        plcs: [],
        loading: false,
        loaded: true,
        error: null
      };

      function PlcReducer() {
        var plcState = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : initialState;
        var action = arguments.length > 1 ? arguments[1] : undefined;

        switch (action.type) {
          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS"]:
            {
              return Object.assign(Object.assign({}, plcState), {
                loading: true
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS_SUCCESS"]:
            {
              return Object.assign(Object.assign({}, plcState), {
                plcs: _toConsumableArray(action.payload),
                loading: false,
                loaded: true
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS_FAIL"]:
            {
              return Object.assign(Object.assign({}, plcState), {
                error: action.payload,
                loading: false,
                loaded: false
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC"]:
            {
              return Object.assign({}, plcState);
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC_SUCCESS"]:
            {
              var plcs = [].concat(_toConsumableArray(plcState.plcs), [action.payload]);
              return Object.assign(Object.assign({}, plcState), {
                plcs: plcs
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC"]:
            {
              return Object.assign({}, plcState);
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC_SUCCESS"]:
            {
              var _plcs = _toConsumableArray(plcState.plcs.filter(function (x) {
                return x.id !== action.payload;
              }));

              return Object.assign(Object.assign({}, plcState), {
                plcs: _plcs
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC"]:
            {
              return Object.assign({}, plcState);
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC_SUCCESS"]:
            {
              var index = plcState.plcs.findIndex(function (x) {
                return x.id === action.payload.id;
              });

              var copy = _toConsumableArray(plcState.plcs);

              copy[index] = action.payload;
              return Object.assign(Object.assign({}, plcState), {
                plcs: _toConsumableArray(copy)
              });
            }

          case _actions__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC_FAIL"]:
          case _actions__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC_FAIL"]:
          case _actions__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC_FAIL"]:
            {
              return Object.assign(Object.assign({}, plcState), {
                error: action.payload
              });
            }

          default:
            {
              return plcState;
            }
        }
      }
      /***/

    },

    /***/
    "sIB3":
    /*!****************************************************************!*\
      !*** ./src/app/shared/components/no-data/no-data.component.ts ***!
      \****************************************************************/

    /*! exports provided: NoDataComponent */

    /***/
    function sIB3(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "NoDataComponent", function () {
        return NoDataComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _animations_animations__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ../../animations/animations */
      "fk77");

      var NoDataComponent = function NoDataComponent() {
        _classCallCheck(this, NoDataComponent);

        this.image = 'no-data';
      };

      NoDataComponent.ɵfac = function NoDataComponent_Factory(t) {
        return new (t || NoDataComponent)();
      };

      NoDataComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: NoDataComponent,
        selectors: [["app-no-data"]],
        inputs: {
          title: "title",
          text: "text",
          image: "image"
        },
        decls: 6,
        vars: 4,
        consts: [[1, "flex", "flex-col", "items-center", "bg-gray-100", "rounded", "p-8", "h-full"], ["alt", "no-data", 1, "w-64", "mb-6", 3, "src"], [1, "text-gray-500", "text-xl", "mb-4"], [1, "text-sm", "text-gray-600"]],
        template: function NoDataComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "img", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "h2", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "p", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("@fadeInFadeOut", undefined);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("src", "../assets/img/" + ctx.image + ".svg", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsanitizeUrl"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.title);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.text);
          }
        },
        encapsulation: 2,
        data: {
          animation: [_animations_animations__WEBPACK_IMPORTED_MODULE_1__["fadeInFadeOut"]]
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](NoDataComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-no-data',
            template: "\n    <div [@fadeInFadeOut] class=\"flex flex-col items-center bg-gray-100 rounded p-8 h-full\">\n      <img class=\"w-64 mb-6\" [src]=\"'../assets/img/' + image + '.svg'\" alt=\"no-data\" />\n      <h2 class=\"text-gray-500 text-xl mb-4\">{{ title }}</h2>\n      <p class=\"text-sm text-gray-600\">{{ text }}</p>\n    </div>\n  ",
            animations: [_animations_animations__WEBPACK_IMPORTED_MODULE_1__["fadeInFadeOut"]]
          }]
        }], null, {
          title: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          text: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          image: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }]
        });
      })();
      /***/

    },

    /***/
    "tg95":
    /*!*****************************************!*\
      !*** ./src/app/store/reducers/index.ts ***!
      \*****************************************/

    /*! exports provided: reducers */

    /***/
    function tg95(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "reducers", function () {
        return reducers;
      });
      /* harmony import */


      var _plc_reducer__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./plc.reducer */
      "psMM");
      /* harmony import */


      var _tool_reducer__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./tool.reducer */
      "CKoO");

      var reducers = {
        plcs: _plc_reducer__WEBPACK_IMPORTED_MODULE_0__["PlcReducer"],
        tools: _tool_reducer__WEBPACK_IMPORTED_MODULE_1__["ToolReducer"]
      };
      /***/
    },

    /***/
    "to+R":
    /*!*******************************************************!*\
      !*** ./src/app/shared/websocket/websocket.service.ts ***!
      \*******************************************************/

    /*! exports provided: WebsocketService */

    /***/
    function toR(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "WebsocketService", function () {
        return WebsocketService;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @ngrx/store */
      "l7P3");
      /* harmony import */


      var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! rxjs */
      "qCKp");
      /* harmony import */


      var _actions__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @actions */
      "v8Ou");
      /* harmony import */


      var src_environments_environment__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! src/environments/environment */
      "AytR");
      /* harmony import */


      var stompjs__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! stompjs */
      "dNE1");
      /* harmony import */


      var stompjs__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(stompjs__WEBPACK_IMPORTED_MODULE_5__);

      var WebsocketService = /*#__PURE__*/function () {
        function WebsocketService(store) {
          _classCallCheck(this, WebsocketService);

          this.store = store;
          this.socket = new WebSocket(src_environments_environment__WEBPACK_IMPORTED_MODULE_4__["environment"].url_webscoket);
          this.ws = Object(stompjs__WEBPACK_IMPORTED_MODULE_5__["over"])(this.socket);
          this.disconnected = true;
        }

        _createClass(WebsocketService, [{
          key: "connect",
          value: function connect() {
            var _this5 = this;

            this.ws.connect({}, function () {
              _this5.ws.debug = true;
            }, function (err) {
              return !_this5.disconnected && console.error(err);
            });
            this.ws.reconnect_delay = 500;
          }
        }, {
          key: "disconnect",
          value: function disconnect() {
            if (this.ws) {
              this.ws.ws.close();
              this.disconnected = true;
            }
          }
        }, {
          key: "sendMessage",
          value: function sendMessage(destination, message) {
            if (this.ws) {
              this.ws.send(destination, {}, message);
              this.disconnected = false;
            }
          }
        }, {
          key: "sub",
          value: function sub(topic, update) {
            var _this6 = this;

            var _a;

            var subject = new rxjs__WEBPACK_IMPORTED_MODULE_2__["Subject"]();

            if ((_a = this.ws) === null || _a === void 0 ? void 0 : _a.connected) {
              this.subs = this.ws.subscribe(topic, function (msg) {
                subject.next(msg.body);
                var data = JSON.parse(msg.body);
                (data === null || data === void 0 ? void 0 : data.newTool) && _this6.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["AddNewTool"](data.newTool));
                data.id && data.toolNumber >= 0 && _this6.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["CurrentlyUsed"](data));
              });
            }

            return subject;
          }
        }, {
          key: "unsub",
          value: function unsub() {
            if (this.subs) {
              this.subs.unsubscribe();
            }
          }
        }]);

        return WebsocketService;
      }();

      WebsocketService.ɵfac = function WebsocketService_Factory(t) {
        return new (t || WebsocketService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]));
      };

      WebsocketService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: WebsocketService,
        factory: WebsocketService.ɵfac,
        providedIn: 'root'
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](WebsocketService, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
          args: [{
            providedIn: 'root'
          }]
        }], function () {
          return [{
            type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "v47b":
    /*!**********************************************!*\
      !*** ./src/app/store/effects/plc.effects.ts ***!
      \**********************************************/

    /*! exports provided: PlcEffect */

    /***/
    function v47b(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcEffect", function () {
        return PlcEffect;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @ngrx/effects */
      "9jGm");
      /* harmony import */


      var _shared_services_plc_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ../../shared/services/plc.service */
      "w9+w");
      /* harmony import */


      var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @ngrx/store */
      "l7P3");
      /* harmony import */


      var _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ../actions/plc.action */
      "//oq");
      /* harmony import */


      var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! rxjs/operators */
      "kU1M");
      /* harmony import */


      var rxjs__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! rxjs */
      "qCKp");

      var PlcEffect = function PlcEffect(action$, plcService, store) {
        var _this7 = this;

        _classCallCheck(this, PlcEffect);

        this.action$ = action$;
        this.plcService = plcService;
        this.store = store;
        this.getPlcs$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["createEffect"])(function () {
          return _this7.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["LOAD_PLCS"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["concatMap"])(function (action) {
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_6__["of"])(action).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["withLatestFrom"])(_this7.store.select(function (state) {
              return state.plcs.plcs;
            })));
          }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["mergeMap"])(function () {
            return _this7.plcService.getPlcs().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["map"])(function (plcs) {
              return new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["LoadPlcsSuccess"](plcs);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_6__["of"])(new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["LoadPlcsFail"](error));
            }));
          }));
        });
        this.createPlc$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["createEffect"])(function () {
          return _this7.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["CREATE_PLC"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["mergeMap"])(function (action) {
            return _this7.plcService.createPlc(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["map"])(function (data) {
              return new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["CreatePlcSuccess"](data);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_6__["of"])(new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["CreatePlcFail"](error));
            }));
          }));
        });
        this.removePlc$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["createEffect"])(function () {
          return _this7.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["REMOVE_PLC"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["mergeMap"])(function (action) {
            return _this7.plcService.removePlc(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["map"])(function () {
              return new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["RemovePlcSuccess"](action.payload);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_6__["of"])(new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["RemovePlcFail"](error));
            }));
          }));
        });
        this.updatePlc$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["createEffect"])(function () {
          return _this7.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["UPDATE_PLC"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["mergeMap"])(function (action) {
            return _this7.plcService.updatePlc(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["map"])(function (data) {
              return new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["UpdatePlcSuccess"](data);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_5__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_6__["of"])(new _actions_plc_action__WEBPACK_IMPORTED_MODULE_4__["UpdatePlcFail"](error));
            }));
          }));
        });
      };

      PlcEffect.ɵfac = function PlcEffect_Factory(t) {
        return new (t || PlcEffect)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_shared_services_plc_service__WEBPACK_IMPORTED_MODULE_2__["PlcService"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]));
      };

      PlcEffect.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: PlcEffect,
        factory: PlcEffect.ɵfac
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PlcEffect, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"]
        }], function () {
          return [{
            type: _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]
          }, {
            type: _shared_services_plc_service__WEBPACK_IMPORTED_MODULE_2__["PlcService"]
          }, {
            type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "v8Ou":
    /*!****************************************!*\
      !*** ./src/app/store/actions/index.ts ***!
      \****************************************/

    /*! exports provided: LOAD_PLCS, LOAD_PLCS_SUCCESS, LOAD_PLCS_FAIL, LoadPlcs, LoadPlcsSuccess, LoadPlcsFail, CREATE_PLC, CREATE_PLC_SUCCESS, CREATE_PLC_FAIL, CreatePlc, CreatePlcSuccess, CreatePlcFail, REMOVE_PLC, REMOVE_PLC_SUCCESS, REMOVE_PLC_FAIL, RemovePlc, RemovePlcSuccess, RemovePlcFail, UPDATE_PLC, UPDATE_PLC_SUCCESS, UPDATE_PLC_FAIL, UpdatePlc, UpdatePlcSuccess, UpdatePlcFail, CREATE_TOOL, CREATE_TOOL_SUCCESS, CREATE_TOOL_FAIL, CreateTool, CreateToolSuccess, CreateToolFail, LOAD_TOOLS, LOAD_TOOLS_SUCCESS, LOAD_TOOLS_FAIL, LoadTools, LoadToolsSuccess, LoadToolsFail, UPDATE_TOOL, UPDATE_TOOL_SUCCESS, UPDATE_TOOL_FAIL, UpdateTool, UpdateToolSuccess, UpdateToolFail, DELETE_TOOL, DELETE_TOOL_SUCCESS, DELETE_TOOL_FAIL, DeleteTool, DeleteToolSuccess, DeleteToolFail, ADD_NEW_TOOL, AddNewTool, CURRENTLY_USED, CurrentlyUsed, LOAD_TOOLS_PLC, LOAD_TOOLS_PLC_SUCCESS, LOAD_TOOLS_PLC_FAIL, LoadToolsPLC, LoadToolsPLCSuccess, LoadToolsPLCFail */

    /***/
    function v8Ou(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _plc_action__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./plc.action */
      "//oq");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS_SUCCESS", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_PLCS_FAIL", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LOAD_PLCS_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcs", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LoadPlcs"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcsSuccess", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LoadPlcsSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadPlcsFail", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["LoadPlcsFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC_SUCCESS", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_PLC_FAIL", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CREATE_PLC_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreatePlc", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CreatePlc"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreatePlcSuccess", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CreatePlcSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreatePlcFail", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["CreatePlcFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC_SUCCESS", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "REMOVE_PLC_FAIL", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["REMOVE_PLC_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "RemovePlc", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["RemovePlc"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "RemovePlcSuccess", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["RemovePlcSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "RemovePlcFail", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["RemovePlcFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC_SUCCESS", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_PLC_FAIL", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UPDATE_PLC_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlc", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UpdatePlc"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlcSuccess", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UpdatePlcSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdatePlcFail", function () {
        return _plc_action__WEBPACK_IMPORTED_MODULE_0__["UpdatePlcFail"];
      });
      /* harmony import */


      var _tool_action__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./tool.action */
      "UQwZ");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CREATE_TOOL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL_SUCCESS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CREATE_TOOL_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CREATE_TOOL_FAIL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CREATE_TOOL_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreateTool", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CreateTool"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreateToolSuccess", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CreateToolSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CreateToolFail", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CreateToolFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_SUCCESS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_FAIL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadTools", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadTools"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsSuccess", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadToolsSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsFail", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadToolsFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UPDATE_TOOL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL_SUCCESS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UPDATE_TOOL_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UPDATE_TOOL_FAIL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UPDATE_TOOL_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdateTool", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UpdateTool"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdateToolSuccess", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UpdateToolSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "UpdateToolFail", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["UpdateToolFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DELETE_TOOL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL_SUCCESS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DELETE_TOOL_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DELETE_TOOL_FAIL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DELETE_TOOL_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DeleteTool", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DeleteTool"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DeleteToolSuccess", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DeleteToolSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DeleteToolFail", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["DeleteToolFail"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "ADD_NEW_TOOL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["ADD_NEW_TOOL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "AddNewTool", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["AddNewTool"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CURRENTLY_USED", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CURRENTLY_USED"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "CurrentlyUsed", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["CurrentlyUsed"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS_PLC"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC_SUCCESS", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS_PLC_SUCCESS"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LOAD_TOOLS_PLC_FAIL", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LOAD_TOOLS_PLC_FAIL"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLC", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadToolsPLC"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLCSuccess", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadToolsPLCSuccess"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "LoadToolsPLCFail", function () {
        return _tool_action__WEBPACK_IMPORTED_MODULE_1__["LoadToolsPLCFail"];
      });
      /***/

    },

    /***/
    "vY5A":
    /*!***************************************!*\
      !*** ./src/app/app-routing.module.ts ***!
      \***************************************/

    /*! exports provided: AppRoutingModule */

    /***/
    function vY5A(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function () {
        return AppRoutingModule;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");

      var routes = [{
        path: 'plc',
        loadChildren: function loadChildren() {
          return Promise.all(
          /*! import() | modules-plcs-plcs-module */
          [__webpack_require__.e("default~modules-plcs-plcs-module~modules-tools-tools-module"), __webpack_require__.e("modules-plcs-plcs-module")]).then(__webpack_require__.bind(null,
          /*! ./modules/plcs/plcs.module */
          "GwLj")).then(function (plc) {
            return plc.PlcsModule;
          });
        }
      }, {
        path: 'tool',
        loadChildren: function loadChildren() {
          return Promise.all(
          /*! import() | modules-tools-tools-module */
          [__webpack_require__.e("default~modules-plcs-plcs-module~modules-tools-tools-module"), __webpack_require__.e("modules-tools-tools-module")]).then(__webpack_require__.bind(null,
          /*! ./modules/tools/tools.module */
          "0e1l")).then(function (tool) {
            return tool.ToolsModule;
          });
        }
      }, {
        path: 'viewer',
        loadChildren: function loadChildren() {
          return __webpack_require__.e(
          /*! import() | modules-viewer-viewer-module */
          "modules-viewer-viewer-module").then(__webpack_require__.bind(null,
          /*! ./modules/viewer/viewer.module */
          "38Xm")).then(function (viewer) {
            return viewer.ViewerModule;
          });
        }
      }, {
        path: '',
        pathMatch: 'full',
        redirectTo: 'plc'
      }, {
        path: '**',
        pathMatch: 'full',
        redirectTo: 'plc'
      }];

      var AppRoutingModule = function AppRoutingModule() {
        _classCallCheck(this, AppRoutingModule);
      };

      AppRoutingModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({
        type: AppRoutingModule
      });
      AppRoutingModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({
        factory: function AppRoutingModule_Factory(t) {
          return new (t || AppRoutingModule)();
        },
        imports: [[_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes, {
          relativeLinkResolution: 'legacy',
          useHash: true
        })], _angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](AppRoutingModule, {
          imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]],
          exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](AppRoutingModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
          args: [{
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes, {
              relativeLinkResolution: 'legacy',
              useHash: true
            })],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "w9+w":
    /*!************************************************!*\
      !*** ./src/app/shared/services/plc.service.ts ***!
      \************************************************/

    /*! exports provided: PlcService */

    /***/
    function w9W(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcService", function () {
        return PlcService;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/common/http */
      "tk/3");
      /* harmony import */


      var src_environments_environment__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! src/environments/environment */
      "AytR");

      var PlcService = /*#__PURE__*/function () {
        function PlcService(http) {
          _classCallCheck(this, PlcService);

          this.http = http;
        }

        _createClass(PlcService, [{
          key: "getPlcs",
          value: function getPlcs() {
            return this.http.get("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs"));
          }
        }, {
          key: "createPlc",
          value: function createPlc(plc) {
            if (plc) {
              return this.http.post("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs"), plc);
            }
          }
        }, {
          key: "removePlc",
          value: function removePlc(id) {
            if (id) {
              return this.http["delete"]("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(id));
            }
          }
        }, {
          key: "updatePlc",
          value: function updatePlc(plc) {
            if (plc.id) {
              return this.http.put("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(plc.id), plc);
            }
          }
        }, {
          key: "getPlc",
          value: function getPlc(id) {
            if (id) {
              return this.http.get("".concat(src_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].base_url, "plcs/").concat(id));
            }
          }
        }]);

        return PlcService;
      }();

      PlcService.ɵfac = function PlcService_Factory(t) {
        return new (t || PlcService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]));
      };

      PlcService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({
        token: PlcService,
        factory: PlcService.ɵfac,
        providedIn: 'root'
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PlcService, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
          args: [{
            providedIn: 'root'
          }]
        }], function () {
          return [{
            type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "xu6W":
    /*!***********************************************!*\
      !*** ./src/app/store/effects/tool.effects.ts ***!
      \***********************************************/

    /*! exports provided: ToolEffects */

    /***/
    function xu6W(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ToolEffects", function () {
        return ToolEffects;
      });
      /* harmony import */


      var _ngrx_effects__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @ngrx/effects */
      "9jGm");
      /* harmony import */


      var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @ngrx/store */
      "l7P3");
      /* harmony import */


      var _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ../actions/tool.action */
      "UQwZ");
      /* harmony import */


      var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! rxjs/operators */
      "kU1M");
      /* harmony import */


      var rxjs__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! rxjs */
      "qCKp");
      /* harmony import */


      var _shared_services_tools_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! ../../shared/services/tools.service */
      "UH8I");
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var ToolEffects = function ToolEffects(action$, store, toolService) {
        var _this8 = this;

        _classCallCheck(this, ToolEffects);

        this.action$ = action$;
        this.store = store;
        this.toolService = toolService;
        this.getTools$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["createEffect"])(function () {
          return _this8.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["ofType"])(_actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LOAD_TOOLS"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["concatMap"])(function (action) {
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(action).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["withLatestFrom"])(_this8.store.select(function (state) {
              return state.tools.tools;
            })));
          }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])(function () {
            return _this8.toolService.getTools().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(function (tools) {
              return new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LoadToolsSuccess"](tools);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(function (err) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LoadToolsFail"](err));
            }));
          }));
        });
        this.getToolsPlc$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["createEffect"])(function () {
          return _this8.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["ofType"])(_actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LOAD_TOOLS_PLC"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])(function (action) {
            return _this8.toolService.getToolsByPlc(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(function (data) {
              return new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LoadToolsPLCSuccess"](data);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["LoadToolsPLCFail"](error));
            }));
          }));
        });
        this.createTool$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["createEffect"])(function () {
          return _this8.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["ofType"])(_actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["CREATE_TOOL"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])(function (action) {
            return _this8.toolService.createTool(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(function (data) {
              return new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["CreateToolSuccess"](data);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["CreateToolFail"](error));
            }));
          }));
        });
        this.updateTool$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["createEffect"])(function () {
          return _this8.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["ofType"])(_actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["UPDATE_TOOL"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])(function (action) {
            return _this8.toolService.updateTool(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(function (data) {
              return new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["UpdateToolSuccess"](data);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["UpdateToolFail"](error));
            }));
          }));
        });
        this.deteleTool$ = Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["createEffect"])(function () {
          return _this8.action$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["ofType"])(_actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["DELETE_TOOL"]), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])(function (action) {
            return _this8.toolService.deleteTool(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(function (data) {
              return new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["DeleteToolSuccess"](action.payload);
            }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(function (error) {
              return Object(rxjs__WEBPACK_IMPORTED_MODULE_4__["of"])(new _actions_tool_action__WEBPACK_IMPORTED_MODULE_2__["DeleteToolFail"](error));
            }));
          }));
        });
      };

      ToolEffects.ɵfac = function ToolEffects_Factory(t) {
        return new (t || ToolEffects)(_angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵinject"](_shared_services_tools_service__WEBPACK_IMPORTED_MODULE_5__["ToolsService"]));
      };

      ToolEffects.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdefineInjectable"]({
        token: ToolEffects,
        factory: ToolEffects.ɵfac
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵsetClassMetadata"](ToolEffects, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_6__["Injectable"]
        }], function () {
          return [{
            type: _ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["Actions"]
          }, {
            type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]
          }, {
            type: _shared_services_tools_service__WEBPACK_IMPORTED_MODULE_5__["ToolsService"]
          }];
        }, null);
      })();
      /***/

    },

    /***/
    "xy3U":
    /*!******************************************************************************!*\
      !*** ./src/app/shared/components/busy-indicator/busy-indicator.component.ts ***!
      \******************************************************************************/

    /*! exports provided: BusyIndicatorComponent */

    /***/
    function xy3U(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "BusyIndicatorComponent", function () {
        return BusyIndicatorComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! rxjs/operators */
      "kU1M");
      /* harmony import */


      var _services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ../../services/busy-indicator.service */
      "l1xV");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      function BusyIndicatorComponent_div_0_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "div", 2);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx_r0.color);
        }
      }

      var BusyIndicatorComponent = /*#__PURE__*/function () {
        function BusyIndicatorComponent(busyIndicator) {
          _classCallCheck(this, BusyIndicatorComponent);

          this.busyIndicator = busyIndicator;
          this.show = false;
          this.color = 'dark';
        }

        _createClass(BusyIndicatorComponent, [{
          key: "ngOnInit",
          value: function ngOnInit() {
            var _this9 = this;

            this.subscription = this.busyIndicator.subscribeToState().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["distinctUntilChanged"])()).subscribe(function (state) {
              _this9.show = state;
            });
          }
        }, {
          key: "ngOnDestroy",
          value: function ngOnDestroy() {
            this.subscription && this.subscription.unsubscribe();
          }
        }]);

        return BusyIndicatorComponent;
      }();

      BusyIndicatorComponent.ɵfac = function BusyIndicatorComponent_Factory(t) {
        return new (t || BusyIndicatorComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_2__["BusyIndicatorService"]));
      };

      BusyIndicatorComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: BusyIndicatorComponent,
        selectors: [["busy-indicator"]],
        inputs: {
          color: "color"
        },
        decls: 1,
        vars: 1,
        consts: [["class", "flex justify-center items-center holder  bg-opacity-50 bg-gray-300", 4, "ngIf"], [1, "flex", "justify-center", "items-center", "holder", "bg-opacity-50", "bg-gray-300"], [1, "opacity-1", "w-24", "h-24", "rounded-full", "rotating", 3, "ngClass"]],
        template: function BusyIndicatorComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, BusyIndicatorComponent_div_0_Template, 2, 1, "div", 0);
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.show);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_3__["NgIf"], _angular_common__WEBPACK_IMPORTED_MODULE_3__["NgClass"]],
        styles: [".holder[_ngcontent-%COMP%] {\n  position: fixed;\n  z-index: 20;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n}\n\n@-webkit-keyframes rotating {\n  from {\n    transform: rotate(0deg);\n  }\n  to {\n    transform: rotate(360deg);\n  }\n}\n\n@keyframes rotating {\n  from {\n    transform: rotate(0deg);\n  }\n  to {\n    transform: rotate(360deg);\n  }\n}\n\n.rotating[_ngcontent-%COMP%] {\n  -webkit-animation: rotating 2s linear infinite;\n  animation: rotating 2s linear infinite;\n}\n\n.white[_ngcontent-%COMP%] {\n  border-top: solid 3px white;\n  border-left: solid 3px rgba(255, 255, 255, 0.5);\n  border-right: solid 3px rgba(255, 255, 255, 0.5);\n  border-bottom: solid 3px rgba(255, 255, 255, 0.5);\n}\n\n.grey[_ngcontent-%COMP%] {\n  border-top: solid 3px #718096;\n  border-left: solid 3px rgba(113, 128, 150, 0.5);\n  border-right: solid 3px rgba(113, 128, 150, 0.5);\n  border-bottom: solid 3px rgba(113, 128, 150, 0.5);\n}\n\n.dark[_ngcontent-%COMP%] {\n  border-top: solid 4px #2d3748;\n  border-left: solid 4px rgba(45, 55, 72, 0.2);\n  border-right: solid 4px rgba(45, 55, 72, 0.2);\n  border-bottom: solid 4px rgba(45, 55, 72, 0.2);\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvc2hhcmVkL2NvbXBvbmVudHMvYnVzeS1pbmRpY2F0b3IvYnVzeS1pbmRpY2F0b3IuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxlQUFBO0VBQ0EsV0FBQTtFQUNBLE1BQUE7RUFDQSxPQUFBO0VBQ0EsV0FBQTtFQUNBLFlBQUE7QUFDRjs7QUFFQTtFQUNFO0lBQ0UsdUJBQUE7RUFDRjtFQUVBO0lBQ0UseUJBQUE7RUFBRjtBQUNGOztBQUdBO0VBQ0U7SUFDRSx1QkFBQTtFQURGO0VBSUE7SUFDRSx5QkFBQTtFQUZGO0FBQ0Y7O0FBS0E7RUFDRSw4Q0FBQTtFQUNRLHNDQUFBO0FBSFY7O0FBTUE7RUFDRSwyQkFBQTtFQUNBLCtDQUFBO0VBQ0EsZ0RBQUE7RUFDQSxpREFBQTtBQUhGOztBQU1BO0VBQ0UsNkJBQUE7RUFDQSwrQ0FBQTtFQUNBLGdEQUFBO0VBQ0EsaURBQUE7QUFIRjs7QUFNQTtFQUNFLDZCQUFBO0VBQ0EsNENBQUE7RUFDQSw2Q0FBQTtFQUNBLDhDQUFBO0FBSEYiLCJmaWxlIjoic3JjL2FwcC9zaGFyZWQvY29tcG9uZW50cy9idXN5LWluZGljYXRvci9idXN5LWluZGljYXRvci5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5ob2xkZXIge1xuICBwb3NpdGlvbjogZml4ZWQ7XG4gIHotaW5kZXg6IDIwO1xuICB0b3A6IDA7XG4gIGxlZnQ6IDA7XG4gIHdpZHRoOiAxMDAlO1xuICBoZWlnaHQ6IDEwMCU7XG59XG5cbkAtd2Via2l0LWtleWZyYW1lcyByb3RhdGluZyB7XG4gIGZyb20ge1xuICAgIHRyYW5zZm9ybTogcm90YXRlKDBkZWcpO1xuICB9XG5cbiAgdG8ge1xuICAgIHRyYW5zZm9ybTogcm90YXRlKDM2MGRlZyk7XG4gIH1cbn1cblxuQGtleWZyYW1lcyByb3RhdGluZyB7XG4gIGZyb20ge1xuICAgIHRyYW5zZm9ybTogcm90YXRlKDBkZWcpO1xuICB9XG5cbiAgdG8ge1xuICAgIHRyYW5zZm9ybTogcm90YXRlKDM2MGRlZyk7XG4gIH1cbn1cblxuLnJvdGF0aW5nIHtcbiAgLXdlYmtpdC1hbmltYXRpb246IHJvdGF0aW5nIDJzIGxpbmVhciBpbmZpbml0ZTtcbiAgICAgICAgICBhbmltYXRpb246IHJvdGF0aW5nIDJzIGxpbmVhciBpbmZpbml0ZTtcbn1cblxuLndoaXRlIHtcbiAgYm9yZGVyLXRvcDogc29saWQgM3B4IHdoaXRlO1xuICBib3JkZXItbGVmdDogc29saWQgM3B4IHJnYmEoMjU1LCAyNTUsIDI1NSwgMC41KTtcbiAgYm9yZGVyLXJpZ2h0OiBzb2xpZCAzcHggcmdiYSgyNTUsIDI1NSwgMjU1LCAwLjUpO1xuICBib3JkZXItYm90dG9tOiBzb2xpZCAzcHggcmdiYSgyNTUsIDI1NSwgMjU1LCAwLjUpO1xufVxuXG4uZ3JleSB7XG4gIGJvcmRlci10b3A6IHNvbGlkIDNweCAjNzE4MDk2O1xuICBib3JkZXItbGVmdDogc29saWQgM3B4IHJnYmEoIzcxODA5NiwgMC41KTtcbiAgYm9yZGVyLXJpZ2h0OiBzb2xpZCAzcHggcmdiYSgjNzE4MDk2LCAwLjUpO1xuICBib3JkZXItYm90dG9tOiBzb2xpZCAzcHggcmdiYSgjNzE4MDk2LCAwLjUpO1xufVxuXG4uZGFyayB7XG4gIGJvcmRlci10b3A6IHNvbGlkIDRweCAjMmQzNzQ4O1xuICBib3JkZXItbGVmdDogc29saWQgNHB4IHJnYmEoIzJkMzc0OCwgMC4yKTtcbiAgYm9yZGVyLXJpZ2h0OiBzb2xpZCA0cHggcmdiYSgjMmQzNzQ4LCAwLjIpO1xuICBib3JkZXItYm90dG9tOiBzb2xpZCA0cHggcmdiYSgjMmQzNzQ4LCAwLjIpO1xufVxuIl19 */"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](BusyIndicatorComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'busy-indicator',
            template: "<div\n    *ngIf=\"show\"\n    class=\"flex justify-center items-center holder  bg-opacity-50 bg-gray-300\"\n  >\n    <div class=\"opacity-1 w-24 h-24 rounded-full rotating\" [ngClass]=\"color\"></div>\n  </div> ",
            styleUrls: ['./busy-indicator.component.scss']
          }]
        }], function () {
          return [{
            type: _services_busy_indicator_service__WEBPACK_IMPORTED_MODULE_2__["BusyIndicatorService"]
          }];
        }, {
          color: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }]
        });
      })();
      /***/

    },

    /***/
    "zUnb":
    /*!*********************!*\
      !*** ./src/main.ts ***!
      \*********************/

    /*! no exports provided */

    /***/
    function zUnb(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./environments/environment */
      "AytR");
      /* harmony import */


      var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./app/app.module */
      "ZAI4");
      /* harmony import */


      var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/platform-browser */
      "jhN1");

      if (_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].production) {
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
      }

      _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["platformBrowser"]().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])["catch"](function (err) {
        return console.error(err);
      });
      /***/

    },

    /***/
    "zfld":
    /*!****************************************************************************!*\
      !*** ./src/app/shared/modules/modal/components/delete/delete.component.ts ***!
      \****************************************************************************/

    /*! exports provided: DeleteComponent */

    /***/
    function zfld(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "DeleteComponent", function () {
        return DeleteComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var DeleteComponent = /*#__PURE__*/function () {
        function DeleteComponent() {
          _classCallCheck(this, DeleteComponent);

          this.action = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.cancel = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        }

        _createClass(DeleteComponent, [{
          key: "onCancel",
          value: function onCancel() {
            this.cancel.emit();
          }
        }, {
          key: "onAction",
          value: function onAction() {
            this.action.emit();
          }
        }]);

        return DeleteComponent;
      }();

      DeleteComponent.ɵfac = function DeleteComponent_Factory(t) {
        return new (t || DeleteComponent)();
      };

      DeleteComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: DeleteComponent,
        selectors: [["app-delete-confirmation"]],
        inputs: {
          title: "title",
          text: "text",
          actionName: "actionName"
        },
        outputs: {
          action: "action",
          cancel: "cancel"
        },
        decls: 18,
        vars: 3,
        consts: [[1, "bg-white", "px-4", "pt-5", "pb-4", "sm:p-6", "sm:pb-4", "sm:max-w-lg", "sm:w-full"], [1, "sm:flex", "sm:items-start"], [1, "mx-auto", "flex-shrink-0", "flex", "items-center", "justify-center", "h-12", "w-12", "rounded-full", "bg-red-100", "sm:mx-0", "sm:h-10", "sm:w-10"], ["fill", "none", "viewBox", "0 0 24 24", "stroke", "currentColor", 1, "h-6", "w-6", "text-red-600"], ["stroke-linecap", "round", "stroke-linejoin", "round", "stroke-width", "2", "d", "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"], [1, "mt-3", "text-center", "sm:mt-0", "sm:ml-4", "sm:text-left"], ["id", "modal-headline", 1, "text-lg", "leading-6", "font-medium", "text-gray-900"], [1, "mt-2"], [1, "text-sm", "leading-5", "text-gray-600"], [1, "bg-gray-100", "px-4", "py-3", "sm:px-6", "sm:flex", "sm:flex-row-reverse"], [1, "flex", "w-full", "rounded-md", "shadow-sm", "sm:ml-3", "sm:w-auto"], ["type", "button", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-transparent", "px-4", "py-2", "bg-red-600", "text-base", "leading-6", "font-medium", "text-white", "shadow-sm", "hover:bg-red-500", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", 3, "click"], [1, "mt-3", "flex", "w-full", "rounded-md", "shadow-sm", "sm:mt-0", "sm:w-auto"], ["type", "button", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-gray-300", "px-4", "py-2", "bg-white", "text-base", "leading-6", "font-medium", "text-gray-700", "shadow-sm", "hover:text-gray-500", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", 3, "click"]],
        template: function DeleteComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnamespaceSVG"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "svg", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](4, "path", 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnamespaceHTML"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "h3", 6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "div", 7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "p", 8);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](10);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](11, "div", 9);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "span", 10);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](13, "button", 11);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function DeleteComponent_Template_button_click_13_listener() {
              return ctx.onAction();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](14);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](15, "span", 12);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](16, "button", 13);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function DeleteComponent_Template_button_click_16_listener() {
              return ctx.onCancel();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](17, " Cancel ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.title, " ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.text, " ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.actionName, " ");
          }
        },
        encapsulation: 2
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](DeleteComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'app-delete-confirmation',
            template: "\n    <div class=\"bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4 sm:max-w-lg sm:w-full\">\n      <div class=\"sm:flex sm:items-start\">\n        <div\n          class=\"mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10\"\n        >\n          <svg class=\"h-6 w-6 text-red-600\" fill=\"none\" viewBox=\"0 0 24 24\" stroke=\"currentColor\">\n            <path\n              stroke-linecap=\"round\"\n              stroke-linejoin=\"round\"\n              stroke-width=\"2\"\n              d=\"M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z\"\n            />\n          </svg>\n        </div>\n        <div class=\"mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left\">\n          <h3 class=\"text-lg leading-6 font-medium text-gray-900\" id=\"modal-headline\">\n            {{ title }}\n          </h3>\n          <div class=\"mt-2\">\n            <p class=\"text-sm leading-5 text-gray-600\">\n              {{ text }}\n            </p>\n          </div>\n        </div>\n      </div>\n    </div>\n\n    <div class=\"bg-gray-100 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse\">\n      <span class=\"flex w-full rounded-md shadow-sm sm:ml-3 sm:w-auto\">\n        <button\n          type=\"button\"\n          class=\"inline-flex justify-center w-full rounded-md border border-transparent px-4 py-2 bg-red-600 text-base leading-6 font-medium text-white shadow-sm hover:bg-red-500 focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5\"\n          (click)=\"onAction()\"\n        >\n          {{ actionName }}\n        </button>\n      </span>\n      <span class=\"mt-3 flex w-full rounded-md shadow-sm sm:mt-0 sm:w-auto\">\n        <button\n          type=\"button\"\n          class=\"inline-flex justify-center w-full rounded-md border border-gray-300 px-4 py-2 bg-white text-base leading-6 font-medium text-gray-700 shadow-sm hover:text-gray-500 focus:outline-none transition ease-in-out duration-150 sm:text-sm sm:leading-5\"\n          (click)=\"onCancel()\"\n        >\n          Cancel\n        </button>\n      </span>\n    </div>\n  "
          }]
        }], null, {
          title: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          text: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          actionName: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          action: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }],
          cancel: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"]
          }]
        });
      })();
      /***/

    },

    /***/
    "zn8P":
    /*!******************************************************!*\
      !*** ./$$_lazy_route_resource lazy namespace object ***!
      \******************************************************/

    /*! no static exports found */

    /***/
    function zn8P(module, exports) {
      function webpackEmptyAsyncContext(req) {
        // Here Promise.resolve().then() is used instead of new Promise() to prevent
        // uncaught exception popping up in devtools
        return Promise.resolve().then(function () {
          var e = new Error("Cannot find module '" + req + "'");
          e.code = 'MODULE_NOT_FOUND';
          throw e;
        });
      }

      webpackEmptyAsyncContext.keys = function () {
        return [];
      };

      webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
      module.exports = webpackEmptyAsyncContext;
      webpackEmptyAsyncContext.id = "zn8P";
      /***/
    }
  }, [[0, "runtime", "vendor"]]]);
})();
//# sourceMappingURL=main-es5.js.map