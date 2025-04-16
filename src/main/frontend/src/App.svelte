<script lang="ts">
  import { RouterView } from '@dvcol/svelte-simple-router/components';

  import type { Route, RouterOptions } from '@dvcol/svelte-simple-router/models';

  import DemoComponent from "./lib/Demo.svelte";
  import LoginComponent from "./lib/Login.svelte";
  import ReportIncidentComponent from "./lib/ReportIncident.svelte";
  import StudentDashboardComponent from "./lib/Student-Dashboard.svelte";
  import PastReportComponent from "./lib/PastReport.svelte";
  import AdminDashboardComponent from "./lib/Admin-Dashboard.svelte";
  import ManageReportComponent from "./lib/Manage-Report.svelte";
  import ForgotPasswordComponent from "./lib/ForgotPassword.svelte";
  import CreatAccountComponent from "./lib/CreateAccount.svelte";
  import ErrorComponent from './lib/Error.svelte';

  const RouteName = {
      Demo: 'demo',
      Login: 'login',
      Report: 'report',
      Student_Dashboard: 'student_dashboard',
      Admin_Dashboard: 'admin_dashboard',
      Past_Report: 'past-reports',
      Manage_Report: 'manage-report',
      Forgot_Password: 'forgot-password',
      Create_Account: 'create-account',
      Error: 'error',
  } as const;

  type RouteNames = (typeof RouteName)[keyof typeof RouteName];

  export const routes: Readonly<Route<RouteNames>[]> = [
    {
      name: RouteName.Home,
      path: '/',
      component: LoginComponent
    },
    {
      name: RouteName.Demo,
      path: `/${RouteName.Demo}`,
      component: DemoComponent
    },
      {
        name: RouteName.Login,
        path: `/${RouteName.Login}`,
        component: LoginComponent
      },
      {
          name: RouteName.Report,
          path: `/${RouteName.Report}`,
          component: ReportIncidentComponent
      },
      {
          name: RouteName.Student_Dashboard,
          path: `/${RouteName.Student_Dashboard}`,
          component: StudentDashboardComponent
      },
      {
          name: RouteName.Past_Report,
          path: `?${RouteName.Past_Report}`,
          component: PastReportComponent
      },
      {
          name: RouteName.Admin_Dashboard,
          path: `?${RouteName.Admin_Dashboard}`,
          component: AdminDashboardComponent
      },
      {
          name: RouteName.Manage_Report,
          path: `?${RouteName.Manage_Report}`,
          component: ManageReportComponent
      },
      {
          name: RouteName.Forgot_Password,
          path: `?${RouteName.Forgot_Password}`,
          component: ForgotPasswordComponent
      },
      {
          name: RouteName.Create_Account,
          path: `?${RouteName.Create_Account}` ,
          component: CreatAccountComponent
      },
      {
          name: RouteName.Error,
          path: '*' ,
          component: ErrorComponent
      },


  ] as const;

  export const options: RouterOptions<RouteNames> = {
    routes,
  } as const;

</script>

<RouterView {options} />

<style>
    :global(body) {
        background-image: url('assets/GSU-CAMPUS.png');
        background-position: center;
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
        position: relative;
    }

    :global(body)::before {
        content: '';
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        box-shadow: inset 0 0 150px 150px rgba(0, 0, 0, 0.6);
        pointer-events: none;
    }

     :global(img) {
         user-drag: none;
         -webkit-user-drag: none;
         user-select: none;
         -webkit-user-select: none;
         -ms-user-select: none;
     }
</style>