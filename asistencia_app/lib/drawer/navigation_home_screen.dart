import 'package:asistencia_app/comp/CustomAppBar.dart';
import 'package:asistencia_app/theme/AppTheme.dart';
import 'package:asistencia_app/drawer/drawer_user_controller.dart';
import 'package:asistencia_app/drawer/home_drawer.dart';
import 'package:asistencia_app/ui/actividad/actividad_main.dart';
//import 'package:app_upeu/ui/beneficiario/beneficiario_main.dart';
//import 'package:app_upeu/ui/beneficiariobloc/beneficiariob_main.dart';
//import 'package:app_upeu/ui/beneficiariofirebloc/beneficiariof_main.dart';
//import 'package:covidapp/drawer/feedback_screen.dart';
import 'package:asistencia_app/ui/help_screen.dart';
//import 'package:flutter_app/ui/personab/persona_main.dart';
//import 'package:flutter_app/ui/personafire/persona_main.dart';

//import 'package:covidapp/drawer/home_screen.dart';
//import 'package:covidapp/drawer/invite_friend_screen.dart';
import 'package:flutter/material.dart';
//import 'package:asistencia_app/ui/persona/persona_main.dart';

class NavigationHomeScreen extends StatefulWidget {
  @override
  _NavigationHomeScreenState createState() => _NavigationHomeScreenState();
}

class _NavigationHomeScreenState extends State<NavigationHomeScreen> {
  Widget? screenView;
  DrawerIndex? drawerIndex;

  @override
  void initState() {
    drawerIndex = DrawerIndex.HOME;
    screenView = HelpScreen();
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.nearlyWhite,
      child: SafeArea(
        top: false,
        bottom: false,
        child: Scaffold(
          backgroundColor: AppTheme.nearlyWhite,
          //appBar: CustomAppBar(accionx: accion as Function),
          body: DrawerUserController(
            screenIndex: drawerIndex!!,
            drawerWidth: MediaQuery.of(context).size.width * 0.75,
            onDrawerCall: (DrawerIndex drawerIndexdata) {
              changeIndex(drawerIndexdata);
              //callback from drawer for replace screen as user need with passing DrawerIndex(Enum index)
            },
            screenView: screenView!!,
            drawerIsOpen: (bool ) {  },
            //we replace screen view as we need on navigate starting screens like MyHomePage, HelpScreen, FeedbackScreen, etc...
          ),
        ),
      ),
    );
  }

  void changeIndex(DrawerIndex drawerIndexdata) {
    if (drawerIndex != drawerIndexdata) {
      drawerIndex = drawerIndexdata;
      if (drawerIndex == DrawerIndex.HOME) {
        setState(() {
          screenView = HelpScreen(); //MainPersona()
        });
      } else if (drawerIndex == DrawerIndex.FeedBack) {
        setState(() {
          screenView = MainActividad();
        });
      }else if (drawerIndex == DrawerIndex.Help) {
        setState(() {
          //screenView = MainPersonaB();
        });
      }else if (drawerIndex == DrawerIndex.Invite) {
        setState(() {
          //screenView = MainPersonaBFire();
        });
      } else {
        //do in your way......
      }
    }
  }
}
