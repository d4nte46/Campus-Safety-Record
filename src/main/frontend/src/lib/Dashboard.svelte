<script>
    // Props for the component
    export let buttons = []; // Will accept an array of button objects
    export let title = "Navigation Buttons"; // Default title

    // Default button data if none is provided
    $: actualButtons = buttons.length > 0 ? buttons : [
        { id: 1, text: 'Report An Incident', href: '/report' },
    ];

    // Button styling with Tailwind
    const buttonClass = "px-4 py-2 rounded-md font-medium transition-colors duration-200 bg-blue-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 m-2";
</script>

<div class="w-full max-w-3xl p-8 mx-auto bg-gray-100 rounded-md shadow-lg">
    <div class="text-black">Welcome to our Application</div>
    <div class="flex flex-wrap justify-center ">
        {#each actualButtons as button (button.id || crypto.randomUUID())}
            <a
                    href={button.href || '#'}
                    class={buttonClass}
                    data-id={button.id}
                    role="button"
                    tabindex="0"
                    target={button.external ? '_blank' : '_self'}
                    rel={button.external ? 'noopener noreferrer' : ''}
            >
                {button.text || 'Button'}
            </a>
        {/each}
    </div>
</div>