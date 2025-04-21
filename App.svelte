<script>
  // @ts-nocheck
  
    // Props for the component
    export let buttons = []; // Will accept an array of button objects
    export let title = "Navigation Buttons"; // Default title
  
    // Default button data if none is provided
    $: actualButtons = buttons.length > 0 ? buttons : [
        { id: 1, text: 'Report An Incident', href: '/report' },
        { id: 2, text: 'View Reports', href: '/past-reports' },
        { id: 3, text: 'Alerts', href: '/alert-center'},
        { id: 4, text: 'Heatmap', href: '/heat-map'},
        { id: 5, text: 'Safety Tips', href: '/safety-tips'}
    ];
  </script>
  
  <div class="text-black text-center mb-6">
    <h2>{title}</h2>
  </div>
  
  <div class="flex flex-wrap justify-center gap-4">
    {#each actualButtons as button (button.id || crypto.randomUUID())}
        <a href="{button.href}" class="button">
            {button.text || 'Button'}
        </a>
    {/each}
  </div>
  
  <button class="button">
    Logout
  </button>
  
  <style>
    @import "tailwindcss";
  
    @font-face {
        font-family: Madeleina-Sans;
        src: url("../assets/MadeleinaSans-2VY3.ttf");
    }
  
    :root {
        --color-metallic-blue: #234A78;
        --color-gsu-blue: #0039A6;
        --color-jasper-orange: #D3941A;
    }
  
    .button {
        font-family: Madeleina-Sans, sans-serif;
        background-color: var(--color-gsu-blue);
        color: white;
        width: 100px;
        height: 100px;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        border-radius: 8px;
        cursor: pointer;
        transition: transform 0.2s ease, background-color 0.2s ease;
    }
  
    .button:hover {
        background-color: var(--color-jasper-orange);
        transform: scale(1.05);
    }
  </style>
