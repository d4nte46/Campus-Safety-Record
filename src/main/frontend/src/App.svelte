<script lang="ts">
  import { RouterView } from '@dvcol/svelte-simple-router/components';

  import type { Route, RouterOptions } from '@dvcol/svelte-simple-router/models';


  import HomeComponent from './lib/Home.svelte';
  import DemoComponent from "./lib/Demo.svelte";
  import LoginComponent from "./lib/Login.svelte";
  import ReportIncidentComponent from "./lib/ReportIncident.svelte";
  import DashboardComponent from "./lib/Dashboard.svelte";

  const RouteName = {
      Home: 'home',
      Demo: 'demo',
      Login: 'login',
      Report: 'report',
      Dashboard: 'dashboard'
  } as const;

  type RouteNames = (typeof RouteName)[keyof typeof RouteName];

  export const routes: Readonly<Route<RouteNames>[]> = [
    {
      name: RouteName.Home,
      path: '/',
      component: HomeComponent
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
          name: RouteName.Dashboard,
          path: `/${RouteName.Dashboard}`,
          component: DashboardComponent
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
</style>