<!-- PastReports.svelte -->
<script>
    import { slide } from 'svelte/transition';
    import { cubicIn, cubicOut } from 'svelte/easing';
    import GSULogo from "../assets/georgia-state-university-logo-gsu.svg";

    // Mock reports data
    let reports = [
        {
            id: 1,
            type: 'Theft',
            status: 'Under Review',
            location: 'Main Library, 2nd Floor',
            description: 'Laptop stolen from library',
            createdAt: '2025-04-10T14:30:00',
            expanded: false
        },
        {
            id: 2,
            type: 'Suspicious Activity',
            status: 'Resolved',
            location: 'West Hall Entrance',
            description: 'Unknown person trying to enter dorm building',
            createdAt: '2025-04-12T09:15:00',
            expanded: false
        },
        {
            id: 3,
            type: 'Harassment',
            status: 'Investigating',
            location: 'Science Building, Room 301',
            description: 'Verbal harassment during class',
            createdAt: '2025-04-14T11:45:00',
            expanded: false
        },
        {
            id: 4,
            type: 'Safety Hazard',
            status: 'Fixed',
            location: 'Engineering Building, 1st Floor',
            description: 'Broken glass in hallway',
            createdAt: '2025-04-08T16:20:00',
            expanded: false
        }
    ];

    // Format date for display
    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString('en-US', {
            month: 'short',
            day: 'numeric',
            year: 'numeric',
            hour: 'numeric',
            minute: 'numeric'
        });
    }

    // Get status badge color
    function getStatusColor(status) {
        switch(status) {
            case 'Under Review': return 'bg-amber-100 text-amber-800 border-amber-200';
            case 'Resolved': return 'bg-green-100 text-green-800 border-green-200';
            case 'Investigating': return 'bg-blue-100 text-blue-800 border-blue-200';
            case 'Fixed': return 'bg-green-100 text-green-800 border-green-200';
            default: return 'bg-gray-100 text-gray-800 border-gray-200';
        }
    }

    // Toggle report details
    function toggleReportDetails(reportId) {
        reports = reports.map(report => ({
            ...report,
            expanded: report.id === reportId ? !report.expanded : false
        }));
    }
</script>

    <!-- Header -->
    <header>
        <div class="flex justify-between items-center">
            <h1 class="text-2xl font-bold text-slate-900">Past Safety Reports</h1>
            <div class="text-right">
                <img src={GSULogo} alt="Georgia State University Logo" class="w-20 mb-2 text-black"/>
            </div>
        </div>
    </header>

    <main class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
        <!-- Reports List -->
        <div class="bg-white shadow rounded-lg overflow-hidden">
            <div class="divide-y divide-gray-200">
                <!-- Table Header -->
                <div class="bg-gray-50 px-6 py-3 grid grid-cols-12 gap-4">
                    <div class="col-span-1 text-xs font-medium text-gray-500 uppercase tracking-wider">ID</div>
                    <div class="col-span-3 text-xs font-medium text-gray-500 uppercase tracking-wider">Type</div>
                    <div class="col-span-4 text-xs font-medium text-gray-500 uppercase tracking-wider">Date</div>
                    <div class="col-span-3 text-xs font-medium text-gray-500 uppercase tracking-wider">Status</div>
                    <div class="col-span-1 text-xs font-medium text-gray-500 uppercase tracking-wider"></div>
                </div>

                <!-- Reports Rows -->
                {#each reports as report (report.id)}
                    <div class="divide-y divide-gray-100">
                        <!-- Report Row -->
                        <div class="hover:bg-gray-50 px-6 py-4 grid grid-cols-12 gap-4 items-center">
                            <div class="col-span-1 text-sm font-medium text-gray-900">{report.id}</div>
                            <div class="col-span-3 text-sm text-gray-900">{report.type}</div>
                            <div class="col-span-4 text-sm text-gray-500">{formatDate(report.createdAt)}</div>
                            <div class="col-span-3">
                <span class={`px-2 py-1 text-xs rounded-full border ${getStatusColor(report.status)}`}>
                  {report.status}
                </span>
                            </div>
                            <div class="col-span-1 text-right">
                                <button
                                        on:click={() => toggleReportDetails(report.id)}
                                        class="text-blue-600 hover:text-blue-900 flex items-center justify-end"
                                        aria-expanded={report.expanded}
                                >
                                    <span class="sr-only">{report.expanded ? 'Hide' : 'Show'} details</span>
                                    <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            class="h-5 w-5 transform transition-transform {report.expanded ? 'rotate-180' : ''}"
                                            viewBox="0 0 20 20"
                                            fill="currentColor"
                                    >
                                        <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                                    </svg>
                                </button>
                            </div>
                        </div>

                        <!-- Report Details (Expanded) -->
                        {#if report.expanded}
                            <div
                                    class="bg-gray-50 px-6 py-4 grid grid-cols-1 gap-4 transition-all duration-300 ease-in-out max-h-96 overflow-hidden"
                                    in:slide={{ duration: 300, easing: cubicOut }}
                                    out:slide={{ duration: 300, easing: cubicIn }}
                            >
                                <div class="grid md:grid-cols-2 gap-4">
                                    <div>
                                        <p class="text-sm font-medium text-gray-500">Location</p>
                                        <p class="mt-1 text-sm text-black">{report.location}</p>
                                    </div>
                                    <div>
                                        <p class="text-sm font-medium text-gray-500">Date Reported</p>
                                        <p class="mt-1 text-sm text-black">{formatDate(report.createdAt)}</p>
                                    </div>
                                </div>
                                <div>
                                    <p class="text-sm font-medium text-gray-500">Description</p>
                                    <p class="mt-1 text-sm text-black">{report.description}</p>
                                </div>
                            </div>
                        {/if}
                    </div>
                {/each}

                {#if reports.length === 0}
                    <div class="px-6 py-10 text-center text-gray-500">
                        <p>No reports found.</p>
                    </div>
                {/if}
            </div>
        </div>
    </main>
<button on:click={()=>window.location.href='/student_dashboard'}>
    Return to Dashboard
</button>
