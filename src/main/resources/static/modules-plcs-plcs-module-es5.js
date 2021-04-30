(function () {
  function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread(); }

  function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); }

  function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

  function _iterableToArray(iter) { if (typeof Symbol !== "undefined" && Symbol.iterator in Object(iter)) return Array.from(iter); }

  function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) return _arrayLikeToArray(arr); }

  function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

  function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

  function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

  function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

  (window["webpackJsonp"] = window["webpackJsonp"] || []).push([["modules-plcs-plcs-module"], {
    /***/
    "/2MJ":
    /*!*********************************************************!*\
      !*** ./src/app/modules/plcs/container/plc.component.ts ***!
      \*********************************************************/

    /*! exports provided: PlcComponent */

    /***/
    function MJ(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcComponent", function () {
        return PlcComponent;
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


      var _sharedModels__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @sharedModels */
      "TwVa");
      /* harmony import */


      var _actions__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @actions */
      "v8Ou");
      /* harmony import */


      var _models_plc_table__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ../models/plc-table */
      "oBVw");
      /* harmony import */


      var _sharedModules__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! @sharedModules */
      "vH0B");
      /* harmony import */


      var _sharedAnimations__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! @sharedAnimations */
      "aDQ/");
      /* harmony import */


      var src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(
      /*! src/app/shared/websocket/websocket.service */
      "to+R");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");
      /* harmony import */


      var _shared_modules_modal_container_modal_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(
      /*! ../../../shared/modules/modal/container/modal.component */
      "VRmN");
      /* harmony import */


      var _components_connect_plc_connect_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(
      /*! ../components/connect-plc/connect.component */
      "YsVU");
      /* harmony import */


      var _shared_modules_modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(
      /*! ../../../shared/modules/modal/components/delete/delete.component */
      "zfld");
      /* harmony import */


      var _shared_components_table_table_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(
      /*! ../../../shared/components/table/table.component */
      "Xv+k");
      /* harmony import */


      var _shared_components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(
      /*! ../../../shared/components/no-data/no-data.component */
      "sIB3");

      var _c0 = ["deleteModal"];
      var _c1 = ["plcModal"];
      var _c2 = ["plcEditModal"];

      function PlcComponent_ng_template_6_Template(rf, ctx) {
        if (rf & 1) {
          var _r13 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "connect-plc", 10);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function PlcComponent_ng_template_6_Template_connect_plc_cancel_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r13);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](5);

            return _r0.close();
          })("action", function PlcComponent_ng_template_6_Template_connect_plc_action_0_listener($event) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r13);

            var ctx_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](5);

            ctx_r14.plcHandler($event);
            return _r0.close();
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("edit", false);
        }
      }

      function PlcComponent_ng_template_10_Template(rf, ctx) {
        if (rf & 1) {
          var _r16 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "connect-plc", 11);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function PlcComponent_ng_template_10_Template_connect_plc_cancel_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r16);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](9);

            return _r3.close();
          })("action", function PlcComponent_ng_template_10_Template_connect_plc_action_0_listener($event) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r16);

            var ctx_r17 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](9);

            ctx_r17.plcHandler($event);
            return _r3.close();
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("edit", true)("data", ctx_r5.editData);
        }
      }

      function PlcComponent_ng_template_14_Template(rf, ctx) {
        if (rf & 1) {
          var _r19 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-delete-confirmation", 12);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("cancel", function PlcComponent_ng_template_14_Template_app_delete_confirmation_cancel_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r19);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](13);

            return _r6.close();
          })("action", function PlcComponent_ng_template_14_Template_app_delete_confirmation_action_0_listener() {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r19);

            var ctx_r20 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            var _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](13);

            ctx_r20.deletePlc();
            return _r6.close();
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("title", "Remove plc")("text", ctx_r8.removeText)("actionName", "Remove");
        }
      }

      function PlcComponent_app_table_16_Template(rf, ctx) {
        if (rf & 1) {
          var _r22 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "app-table", 13);

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("action", function PlcComponent_app_table_16_Template_app_table_action_0_listener($event) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r22);

            var ctx_r21 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

            return ctx_r21.tableActionsHandler($event);
          });

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        }

        if (rf & 2) {
          var ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();

          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("columns", ctx_r9.columns)("data", ctx_r9.rows);
        }
      }

      function PlcComponent_ng_template_17_Template(rf, ctx) {
        if (rf & 1) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-no-data", 14);
        }

        if (rf & 2) {
          _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("image", "empty")("title", "PLC(s) not found!")("text", "Please create at least one PLC");
        }
      }

      var PlcComponent = /*#__PURE__*/function () {
        // set necessary variables and select data from store
        function PlcComponent(store, ws) {
          _classCallCheck(this, PlcComponent);

          this.store = store;
          this.ws = ws;
          this.columns = [{
            name: 'Name',
            sort_by: false,
            descending: true,
            property_name: 'name',
            cell_type: 'text'
          }, {
            name: 'IP Address',
            sort_by: false,
            descending: true,
            property_name: 'ipAddress',
            cell_type: 'text'
          }, {
            name: 'Serial Number',
            sort_by: false,
            descending: true,
            property_name: 'serialNumber',
            cell_type: 'text'
          }, {
            name: 'Firmware',
            sort_by: false,
            descending: true,
            property_name: 'firmware',
            cell_type: 'text'
          }, {
            name: 'Status',
            sort_by: false,
            descending: true,
            property_name: 'status',
            cell_type: 'status'
          }, {
            name: 'Last status change',
            sort_by: false,
            descending: true,
            property_name: 'lastStatusChange',
            cell_type: 'date'
          }, {
            name: 'Actions',
            sort_by: false,
            cell_type: 'action',
            actions: [{
              type: 'edit',
              icon: 'fa-edit',
              text: 'edit',
              title: 'Edit'
            }, {
              type: 'delete',
              icon: 'fa-trash-alt',
              text: 'delete',
              title: 'Delete'
            }]
          }];
          this.plc$ = this.store.select(function (state) {
            return state.plcs.plcs;
          });
          this.show = false;
          this.plcControl = 'new';
        } // sub to store and pass data to components


        _createClass(PlcComponent, [{
          key: "ngOnInit",
          value: function ngOnInit() {
            var _this = this;

            this.plcSubscription = this.plc$.subscribe(function (response) {
              var _a;

              _this.dataBackup = _toConsumableArray(response);
              (response === null || response === void 0 ? void 0 : response.length) > ((_a = _this.rows) === null || _a === void 0 ? void 0 : _a.length) && _this.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["LoadToolsPLC"](response[response.length - 1].id));
              _this.rows = _toConsumableArray(response === null || response === void 0 ? void 0 : response.map(function (x) {
                return new _models_plc_table__WEBPACK_IMPORTED_MODULE_4__["PlcTable"](x.name, x.ipAddress, x.connection.lastStatusChange, x.hardwareInformation.serialNumber, x.hardwareInformation.firmwareNumber, x.connection.status);
              }));

              _this.rows.sort(function (a, b) {
                return b.name.toLowerCase() > a.name.toLowerCase() ? -1 : 1;
              });
            });
          } // unsub from store

        }, {
          key: "ngOnDestroy",
          value: function ngOnDestroy() {
            this.plcSubscription && this.plcSubscription.unsubscribe();
          }
        }, {
          key: "tableActionsHandler",
          value: function tableActionsHandler(event) {
            switch (event.type) {
              case 'edit':
                {
                  this.editData = Object.assign({}, event.item);
                  this.plcEditModal.open();
                  break;
                }

              case 'delete':
                {
                  this.toRemove = Object.assign({}, this.dataBackup.filter(function (x) {
                    return x.name === event.item.name && x.ipAddress === event.item.ipAddress;
                  })[0]);
                  this.removeText = "Do you really want to remove ".concat(this.toRemove.name, " ?");
                  this.deleteModal.open();
                  break;
                }

              default:
                {
                  break;
                }
            }
          }
        }, {
          key: "plcDialog",
          value: function plcDialog() {
            this.plcModal.open();
          } // call action to delete it from store & BE

        }, {
          key: "deletePlc",
          value: function deletePlc() {
            this.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["RemovePlc"](this.toRemove.id));
          }
        }, {
          key: "plcHandler",
          value: function plcHandler(item) {
            var _a;

            var plc;

            switch (item.edit) {
              case false:
                {
                  plc = new _sharedModels__WEBPACK_IMPORTED_MODULE_2__["PLC"](null, item.data.name, item.data.ipAddress, {
                    lastStatusChange: null,
                    status: null
                  }, {
                    serialNumber: null,
                    firmwareNumber: null
                  }, []);
                  this.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["CreatePlc"](plc));
                  break;
                }

              case true:
                {
                  var index = (_a = this.dataBackup) === null || _a === void 0 ? void 0 : _a.findIndex(function (x) {
                    return x.name === item.data.name || x.ipAddress === item.data.ipAddress;
                  });
                  plc = Object.assign(Object.assign({}, this.dataBackup[index]), {
                    name: item.data.name,
                    ipAddress: item.data.ipAddress
                  });
                  this.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["UpdatePlc"](plc));
                  var find = this.rows.find(function (x) {
                    return x.name === plc.name && x.ipAddress !== plc.ipAddress;
                  });
                  find && this.store.dispatch(new _actions__WEBPACK_IMPORTED_MODULE_3__["LoadToolsPLC"](plc.id));
                  break;
                }

              default:
                {
                  break;
                }
            }
          }
        }]);

        return PlcComponent;
      }();

      PlcComponent.ɵfac = function PlcComponent_Factory(t) {
        return new (t || PlcComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_7__["WebsocketService"]));
      };

      PlcComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: PlcComponent,
        selectors: [["plc"]],
        viewQuery: function PlcComponent_Query(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c0, 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c1, 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵviewQuery"](_c2, 1);
          }

          if (rf & 2) {
            var _t;

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.deleteModal = _t.first);
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.plcModal = _t.first);
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵqueryRefresh"](_t = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵloadQuery"]()) && (ctx.plcEditModal = _t.first);
          }
        },
        decls: 19,
        vars: 6,
        consts: [[1, "w-full", "h-full", "overflow-y-hidden"], [1, "flex", "flex-row", "justify-end", 3, "ngClass"], ["routerLink", "/create-unit", 1, "bg-white", "mx-5", "mb-2", "hover:text-black", "hover:bg-gray-100", "h-auto", "rounded", "text-white", "font-thin", "py-2", "px-4", "border", "border-gray-400", "shadow", "btn", 3, "click"], [3, "closeOnOutsideClick"], ["plcModal", ""], ["modalBody", ""], ["plcEditModal", ""], ["deleteModal", ""], [3, "columns", "data", "action", 4, "ngIf", "ngIfElse"], ["noData", ""], [3, "edit", "cancel", "action"], [3, "edit", "data", "cancel", "action"], [3, "title", "text", "actionName", "cancel", "action"], [3, "columns", "data", "action"], [3, "image", "title", "text"]],
        template: function PlcComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "button", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function PlcComponent_Template_button_click_2_listener() {
              ctx.plcControl = "new";
              ctx.show = true;
              return ctx.plcDialog();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3, " Add PLC ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "app-modal", 3, 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](6, PlcComponent_ng_template_6_Template, 1, 1, "ng-template", null, 5, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "app-modal", 3, 6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](10, PlcComponent_ng_template_10_Template, 1, 2, "ng-template", null, 5, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "app-modal", 3, 7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](14, PlcComponent_ng_template_14_Template, 1, 3, "ng-template", null, 5, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](16, PlcComponent_app_table_16_Template, 1, 2, "app-table", 8);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](17, PlcComponent_ng_template_17_Template, 1, 3, "ng-template", null, 9, _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplateRefExtractor"]);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            var _r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵreference"](18);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", !ctx.rows.length && "bg-gray-100");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("closeOnOutsideClick", false);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.rows.length)("ngIfElse", _r10);
          }
        },
        directives: [_angular_common__WEBPACK_IMPORTED_MODULE_8__["NgClass"], _angular_router__WEBPACK_IMPORTED_MODULE_9__["RouterLink"], _shared_modules_modal_container_modal_component__WEBPACK_IMPORTED_MODULE_10__["ModalComponent"], _angular_common__WEBPACK_IMPORTED_MODULE_8__["NgIf"], _components_connect_plc_connect_component__WEBPACK_IMPORTED_MODULE_11__["ConnectComponent"], _shared_modules_modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_12__["DeleteComponent"], _shared_components_table_table_component__WEBPACK_IMPORTED_MODULE_13__["TableComponent"], _shared_components_no_data_no_data_component__WEBPACK_IMPORTED_MODULE_14__["NoDataComponent"]],
        styles: [".btn[_ngcontent-%COMP%]{background-color: #2d3748; margin-top: 0.5rem; outline: none}", ".btn[_ngcontent-%COMP%]:hover{background-color: #e2e8f0; margin-top: 0.5rem}"],
        data: {
          animation: [_sharedAnimations__WEBPACK_IMPORTED_MODULE_6__["fadeInFadeOut"]]
        }
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PlcComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'plc',
            templateUrl: './plc.component.html',
            styles: ['.btn{background-color: #2d3748; margin-top: 0.5rem; outline: none}', '.btn:hover{background-color: #e2e8f0; margin-top: 0.5rem}'],
            animations: [_sharedAnimations__WEBPACK_IMPORTED_MODULE_6__["fadeInFadeOut"]]
          }]
        }], function () {
          return [{
            type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]
          }, {
            type: src_app_shared_websocket_websocket_service__WEBPACK_IMPORTED_MODULE_7__["WebsocketService"]
          }];
        }, {
          deleteModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['deleteModal']
          }],
          plcModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['plcModal']
          }],
          plcEditModal: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"],
            args: ['plcEditModal']
          }]
        });
      })();
      /***/

    },

    /***/
    "GwLj":
    /*!*********************************************!*\
      !*** ./src/app/modules/plcs/plcs.module.ts ***!
      \*********************************************/

    /*! exports provided: PlcsModule */

    /***/
    function GwLj(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcsModule", function () {
        return PlcsModule;
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


      var _container_plc_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./container/plc.component */
      "/2MJ");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");
      /* harmony import */


      var src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! src/app/shared/shared.module */
      "PCNd");
      /* harmony import */


      var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! @angular/forms */
      "3Pt+");
      /* harmony import */


      var _components_connect_plc_connect_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(
      /*! ./components/connect-plc/connect.component */
      "YsVU");

      var routes = [{
        path: '',
        component: _container_plc_component__WEBPACK_IMPORTED_MODULE_2__["PlcComponent"]
      }];

      var PlcsModule = function PlcsModule() {
        _classCallCheck(this, PlcsModule);
      };

      PlcsModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineNgModule"]({
        type: PlcsModule
      });
      PlcsModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjector"]({
        factory: function PlcsModule_Factory(t) {
          return new (t || PlcsModule)();
        },
        imports: [[_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"].forChild(routes)]]
      });

      (function () {
        (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsetNgModuleScope"](PlcsModule, {
          declarations: [_container_plc_component__WEBPACK_IMPORTED_MODULE_2__["PlcComponent"], _components_connect_plc_connect_component__WEBPACK_IMPORTED_MODULE_6__["ConnectComponent"]],
          imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"]]
        });
      })();

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](PlcsModule, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"],
          args: [{
            declarations: [_container_plc_component__WEBPACK_IMPORTED_MODULE_2__["PlcComponent"], _components_connect_plc_connect_component__WEBPACK_IMPORTED_MODULE_6__["ConnectComponent"]],
            imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], src_app_shared_shared_module__WEBPACK_IMPORTED_MODULE_4__["SharedModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ReactiveFormsModule"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["RouterModule"].forChild(routes)]
          }]
        }], null, null);
      })();
      /***/

    },

    /***/
    "YsVU":
    /*!**************************************************************************!*\
      !*** ./src/app/modules/plcs/components/connect-plc/connect.component.ts ***!
      \**************************************************************************/

    /*! exports provided: ConnectComponent */

    /***/
    function YsVU(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ConnectComponent", function () {
        return ConnectComponent;
      });
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/forms */
      "3Pt+");
      /* harmony import */


      var _sharedModels__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @sharedModels */
      "TwVa");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");

      var ConnectComponent = /*#__PURE__*/function () {
        function ConnectComponent(fb) {
          _classCallCheck(this, ConnectComponent);

          this.fb = fb;
          this.edit = false;
          this.action = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.cancel = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
          this.submitted = false;
          this.plcForm = this.fb.group({
            name: [null, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
            ipAddress: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].pattern('^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$')]]
          });
        }

        _createClass(ConnectComponent, [{
          key: "onCancel",
          value: function onCancel() {
            this.cancel.emit();
          }
        }, {
          key: "onAction",
          value: function onAction() {
            this.action.emit();
          } // check for erors in form control

        }, {
          key: "checkErrors",
          value: function checkErrors(control) {
            var result;
            (this.plcForm.get(control).dirty || this.submitted && !this.plcForm.get(control).dirty) && !this.plcForm.get(control).valid ? result = true : result = false;
            return result;
          }
        }, {
          key: "submit",
          value: function submit() {
            this.submitted = true;
            this.plcForm.valid && this.action.emit({
              data: this.plcForm.value,
              edit: this.edit
            });
          }
        }, {
          key: "data",
          set: function set(data) {
            data && (this._data = data, this.plcForm.patchValue({
              name: data.name,
              ipAddress: data.ipAddress
            }));
          }
        }]);

        return ConnectComponent;
      }();

      ConnectComponent.ɵfac = function ConnectComponent_Factory(t) {
        return new (t || ConnectComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"]));
      };

      ConnectComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({
        type: ConnectComponent,
        selectors: [["connect-plc"]],
        inputs: {
          edit: "edit",
          data: "data"
        },
        outputs: {
          action: "action",
          cancel: "cancel"
        },
        decls: 29,
        vars: 9,
        consts: [[1, "bg-white", "px-4", "pt-5", "pb-4", "sm:p-6", "sm:pb-4", "sm:max-w-lg", "sm:w-full"], [1, "sm:flex", "sm:items-start"], [1, "text-center", "sm:mt-0", "sm:ml-4", "sm:text-left"], ["id", "modal-headline", 1, "text-lg", "mt-2", "leading-6", "font-medium", "text-gray-700", "align-middle"], [1, "fas", "fa-robot"], [1, "ml-2"], [1, "mt-2"], [1, "w-full", 3, "formGroup", "ngSubmit"], [1, "flex", "flex-row"], [1, "flex", "flex-col"], ["for", "name", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], [1, "text-red-500"], ["id", "name", "type", "text", "formControlName", "name", 1, "appearance-none", "block", "w-40", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-1", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], [1, "ml-2", "flex", "flex-col"], ["for", "ip", 1, "block", "text-sm", "font-bold", "mb-2", 3, "ngClass"], ["id", "ip", "type", "text", "formControlName", "ipAddress", 1, "appearance-none", "block", "w-40", "h-8", "bg-gray-200", "text-gray-700", "disabled:text-gray-500", "border", "rounded", "py-3", "px-4", "mb-2", "leading-tight", "focus:outline-none", "focus:bg-white", "text-xs", 3, "ngClass"], [1, "bg-gray-100", "px-4", "py-3", "sm:px-6", "sm:flex", "sm:flex-row-reverse"], [1, "flex", "w-full", "rounded-md", "shadow-sm", "sm:ml-3", "sm:w-auto"], ["type", "submit", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-transparent", "px-4", "py-2", "text-base", "leading-6", "font-medium", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", 3, "ngClass", "click"], [1, "mt-3", "flex", "w-full", "rounded-md", "shadow-sm", "sm:mt-0", "sm:w-auto"], ["type", "button", 1, "inline-flex", "justify-center", "w-full", "rounded-md", "border", "border-gray-300", "px-4", "py-2", "bg-white", "text-base", "leading-6", "font-medium", "text-gray-700", "shadow-sm", "focus:outline-none", "transition", "ease-in-out", "duration-150", "sm:text-sm", "sm:leading-5", "hover:text-gray-700", "hover:font-semibold", 3, "click"]],
        template: function ConnectComponent_Template(rf, ctx) {
          if (rf & 1) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "h3", 3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](4, "i", 4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "span", 5);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "div", 6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "form", 7);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngSubmit", function ConnectComponent_Template_form_ngSubmit_8_listener() {
              return ctx.submit();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "div", 8);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "div", 9);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](11, "label", 10);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](12, "Plc name");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](13, "span", 11);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](14, "*");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](15, "input", 12);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](16, "div", 13);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](17, "label", 14);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](18, "IP Address");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](19, "span", 11);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](20, "*");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](21, "input", 15);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](22, "div", 16);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](23, "span", 17);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](24, "button", 18);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ConnectComponent_Template_button_click_24_listener() {
              return ctx.submit();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](25);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](26, "span", 19);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](27, "button", 20);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function ConnectComponent_Template_button_click_27_listener() {
              return ctx.onCancel();
            });

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](28, " Cancel ");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
          }

          if (rf & 2) {
            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.edit ? "Edit Machine" : "Connect to new Machine");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("formGroup", ctx.plcForm);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("name") ? "text-red-500" : "text-gray-700");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("name") ? "border-red-500" : "bg-gray-200");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("ipAddress") ? "text-red-500" : "text-gray-700");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.checkErrors("ipAddress") ? "border-red-500" : "bg-gray-200");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", ctx.plcForm.invalid ? "cursor-not-allowed bg-gray-200 text-gray-400" : "text-white cursor-pointer btn hover:text-black");

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵattribute"]("disabled", ctx.plcForm.invalid ? true : null);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);

            _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate1"](" ", ctx.edit ? "Update" : "Connect", " ");
          }
        },
        directives: [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormGroupDirective"], _angular_common__WEBPACK_IMPORTED_MODULE_3__["NgClass"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["DefaultValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormControlName"]],
        styles: [".btn[_ngcontent-%COMP%]{background-color: #2d3748; outline: none}", ".btn[_ngcontent-%COMP%]:hover{background-color: #e2e8f0}"]
      });

      (function () {
        (typeof ngDevMode === "undefined" || ngDevMode) && _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](ConnectComponent, [{
          type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
          args: [{
            selector: 'connect-plc',
            templateUrl: './connect.component.html',
            styles: ['.btn{background-color: #2d3748; outline: none}', '.btn:hover{background-color: #e2e8f0}']
          }]
        }], function () {
          return [{
            type: _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"]
          }];
        }, {
          edit: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
          }],
          data: [{
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
    "aDQ/":
    /*!********************************************!*\
      !*** ./src/app/shared/animations/index.ts ***!
      \********************************************/

    /*! exports provided: fadeInFadeOut, toggle, fader */

    /***/
    function aDQ(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _animations__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./animations */
      "fk77");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "fadeInFadeOut", function () {
        return _animations__WEBPACK_IMPORTED_MODULE_0__["fadeInFadeOut"];
      });
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "toggle", function () {
        return _animations__WEBPACK_IMPORTED_MODULE_0__["toggle"];
      });
      /* harmony import */


      var _route_animations__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./route-animations */
      "gn+R");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "fader", function () {
        return _route_animations__WEBPACK_IMPORTED_MODULE_1__["fader"];
      });
      /***/

    },

    /***/
    "oBVw":
    /*!**************************************************!*\
      !*** ./src/app/modules/plcs/models/plc-table.ts ***!
      \**************************************************/

    /*! exports provided: PlcTable */

    /***/
    function oBVw(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "PlcTable", function () {
        return PlcTable;
      });

      var PlcTable = function PlcTable(name, ipAddress, lastStatusChange, serialNumber, firmware, status) {
        _classCallCheck(this, PlcTable);

        this.name = name;
        this.ipAddress = ipAddress;
        this.lastStatusChange = lastStatusChange;
        this.serialNumber = serialNumber;
        this.firmware = firmware;
        this.status = status;
      };
      /***/

    },

    /***/
    "vH0B":
    /*!*****************************************!*\
      !*** ./src/app/shared/modules/index.ts ***!
      \*****************************************/

    /*! exports provided: ModalComponent, DeleteComponent */

    /***/
    function vH0B(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony import */


      var _modal_container_modal_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! ./modal/container/modal.component */
      "VRmN");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "ModalComponent", function () {
        return _modal_container_modal_component__WEBPACK_IMPORTED_MODULE_0__["ModalComponent"];
      });
      /* harmony import */


      var _modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! ./modal/components/delete/delete.component */
      "zfld");
      /* harmony reexport (safe) */


      __webpack_require__.d(__webpack_exports__, "DeleteComponent", function () {
        return _modal_components_delete_delete_component__WEBPACK_IMPORTED_MODULE_1__["DeleteComponent"];
      });
      /***/

    }
  }]);
})();
//# sourceMappingURL=modules-plcs-plcs-module-es5.js.map