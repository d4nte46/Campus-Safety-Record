<script>
    // Props for the component
    export let buttons = []; // Will accept an array of button objects
    export let title = "Navigation Buttons"; // Default title

    // Default button data if none is provided
    $: actualButtons = buttons.length > 0 ? buttons : [
        { id: 1, text: 'Report An Incident', href: '/report' },
        { id: 2, text: 'View Reports', href: '/past-reports' },
    ];

    // Button styling with Tailwind
</script>

<div class="text-black ">
    Welcome to our Application
</div>
<div class="flex flex-wrap justify-center ">
    {#each actualButtons as button (button.id || crypto.randomUUID())}
        <button on:click={()=>window.location.href=[button.href]}>
            {button.text || 'Button'}
        </button>
    {/each}
</div>
<button>
    Logout
</button>