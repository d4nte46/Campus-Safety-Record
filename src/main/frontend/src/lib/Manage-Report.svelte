<!-- App.svelte -->
<script>
    import GSULogo from "../assets/georgia-state-university-logo-gsu.svg";

    // Mock data using a hashmap
    let reports = [
        {
            reportId: 1,
            userId: 101,
            reportType: "Suspicious Activity",
            description: "Person loitering near the science building entrance",
            location: "Science Building - North Entrance",
            reportStatus: "Active",
            createdAt: "2025-04-15T14:30:00"
        },
        {
            reportId: 2,
            userId: 102,
            reportType: "Maintenance",
            description: "Broken light fixture in stairwell",
            location: "Library - Second Floor Stairwell",
            reportStatus: "Active",
            createdAt: "2025-04-16T09:15:00"
        },
        {
            reportId: 3,
            userId: 103,
            reportType: "Security Concern",
            description: "Door not locking properly",
            location: "Student Union - South Wing",
            reportStatus: "In Progress",
            createdAt: "2025-04-14T16:45:00"
        },
        {
            reportId: 4,
            userId: 104,
            reportType: "Medical Emergency",
            description: "Student feeling dizzy in class",
            location: "Engineering Hall - Room 302",
            reportStatus: "Resolved",
            createdAt: "2025-04-13T11:20:00"
        },
        {
            reportId: 5,
            userId: 105,
            reportType: "Theft",
            description: "Laptop missing from study area",
            location: "Main Library - First Floor",
            reportStatus: "Flagged",
            createdAt: "2025-04-16T08:45:00"
        }
    ];

    // Display variables
    let filterStatus = "all";
    let searchTerm = "";

    // Report management functions
    function updateStatus(reportId, newStatus) {
        reports = reports.map(report =>
            report.reportId === reportId
                ? {...report, reportStatus: newStatus}
                : report
        );
    }

    // Filter and search functions
    $: filteredReports = reports
        .filter(report =>
            filterStatus === "all" || report.reportStatus === filterStatus
        )
        .filter(report =>
            searchTerm === "" ||
            report.reportType.toLowerCase().includes(searchTerm.toLowerCase()) ||
            report.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
            report.location.toLowerCase().includes(searchTerm.toLowerCase())
        );

    // Format date for display
    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString();
    }

    // Status badge colors
    function getStatusColor(status) {
        switch(status) {
            case "Active": return "bg-yellow-100 text-yellow-800";
            case "In Progress": return "bg-blue-100 text-blue-800";
            case "Resolved": return "bg-green-100 text-green-800";
            case "Flagged": return "bg-red-100 text-red-800";
            default: return "bg-gray-100 text-gray-800";
        }
    }
</script>

<div class="overflow-auto">
    <!-- Admin Dashboard -->
    <!-- Admin Dashboard -->
    <div class="flex flex-col min-h-screen">
        <!-- Header -->
        <header >
            <div class="flex justify-between items-center">
                <h1 class="text-2xl font-bold text-slate-900">Active Safety Reports</h1>
                <div class="text-right">
                    <img src={GSULogo} alt="Georgia State University Logo" class="w-20 mb-2 text-black"/>
                </div>
            </div>
        </header>

        <!-- Main Content -->
        <main class="flex-1 py-6">
            <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                <!-- Filters and Search -->
                <div class="pb-5 border-b border-gray-200 sm:flex sm:items-center sm:justify-between">
                    <h2 class="text-lg font-medium text-gray-900">Active Reports</h2>
                    <div class="mt-3 sm:mt-0 sm:ml-4 flex flex-col sm:flex-row gap-2">
                        <div class="flex rounded-md shadow-sm">
                            <select
                                    bind:value={filterStatus}
                                    class="block w-full rounded-md border-0 py-1.5 pl-3 pr-10 text-gray-900 ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-blue-600 sm:text-sm sm:leading-6"
                            >
                                <option value="all">All Statuses</option>
                                <option value="Active">Active</option>
                                <option value="In Progress">In Progress</option>
                                <option value="Resolved">Resolved</option>
                                <option value="Flagged">Flagged</option>
                            </select>
                        </div>

                        <div class="relative flex-grow">
                            <input
                                    type="text"
                                    placeholder="Search reports..."
                                    bind:value={searchTerm}
                                    class="block w-full rounded-md border-0 py-1.5 pl-3 pr-10 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-blue-600 sm:text-sm sm:leading-6"
                            />
                        </div>
                    </div>
                </div>

                <!-- Reports Table -->
                <div class="mt-6 overflow-auto shadow ring-1 ring-black ring-opacity-5 sm:rounded-lg">
                    <table class="min-w-full divide-y divide-gray-300">
                        <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900">Report Type</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Description</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Location</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Date Reported</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Status</th>
                            <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Actions</th>
                        </tr>
                        </thead>
                        <tbody class="divide-y divide-gray-200 bg-white">
                        {#each filteredReports as report (report.reportId)}
                            <tr>
                                <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900">{report.reportType}</td>
                                <td class="px-3 py-4 text-sm text-gray-500">{report.description}</td>
                                <td class="px-3 py-4 text-sm text-gray-500">{report.location}</td>
                                <td class="px-3 py-4 text-sm text-gray-500">{formatDate(report.createdAt)}</td>
                                <td class="px-3 py-4 text-sm text-gray-500">
                      <span class={`inline-flex rounded-full px-2 text-xs font-semibold leading-5 ${getStatusColor(report.reportStatus)}`}>
                        {report.reportStatus}
                      </span>
                                </td>
                                <td class="px-3 py-4 text-sm text-gray-500">
                                    <div class="flex gap-2">
                                        {#if report.reportStatus !== "In Progress"}
                                            <button
                                                    on:click={() => updateStatus(report.reportId, "In Progress")}
                                                    class="inline-flex items-center rounded bg-blue-50 px-2 py-1 text-xs font-medium text-blue-700 hover:bg-blue-100"
                                            >
                                                Working
                                            </button>
                                        {/if}

                                        {#if report.reportStatus !== "Resolved"}
                                            <button
                                                    on:click={() => updateStatus(report.reportId, "Resolved")}
                                                    class="inline-flex items-center rounded bg-green-50 px-2 py-1 text-xs font-medium text-green-700 hover:bg-green-100"
                                            >
                                                Resolve
                                            </button>
                                        {/if}

                                        {#if report.reportStatus !== "Flagged"}
                                            <button
                                                    on:click={() => updateStatus(report.reportId, "Flagged")}
                                                    class="inline-flex items-center rounded bg-red-50 px-2 py-1 text-xs font-medium text-red-700 hover:bg-red-100"
                                            >
                                                Flag
                                            </button>
                                        {/if}
                                    </div>
                                </td>
                            </tr>
                        {:else}
                            <tr>
                                <td colspan="6" class="px-3 py-4 text-sm text-center text-gray-500">No reports found</td>
                            </tr>
                        {/each}
                        </tbody>
                    </table>
                </div>

                <!-- Status Summary -->
                <div class="mt-6 grid grid-cols-1 gap-4 sm:grid-cols-4">
                    <div class="bg-white overflow-hidden shadow rounded-lg">
                        <div class="px-4 py-5 sm:p-6">
                            <div class="flex items-center">
                                <div class="flex-shrink-0 bg-yellow-100 rounded-md p-3">
                                    <div class="h-6 w-6 text-yellow-600">
                                        <!-- Alert Icon -->
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                                        </svg>
                                    </div>
                                </div>
                                <div class="ml-5 w-0 flex-1">
                                    <dl>
                                        <dt class="text-sm font-medium text-gray-500 truncate">Active</dt>
                                        <dd class="flex items-baseline">
                                            <div class="text-2xl font-semibold text-gray-900">
                                                {reports.filter(r => r.reportStatus === "Active").length}
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="bg-white overflow-hidden shadow rounded-lg">
                        <div class="px-4 py-5 sm:p-6">
                            <div class="flex items-center">
                                <div class="flex-shrink-0 bg-blue-100 rounded-md p-3">
                                    <div class="h-6 w-6 text-blue-600">
                                        <!-- Clock Icon -->
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                                        </svg>
                                    </div>
                                </div>
                                <div class="ml-5 w-0 flex-1">
                                    <dl>
                                        <dt class="text-sm font-medium text-gray-500 truncate">In Progress</dt>
                                        <dd class="flex items-baseline">
                                            <div class="text-2xl font-semibold text-gray-900">
                                                {reports.filter(r => r.reportStatus === "In Progress").length}
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="bg-white overflow-hidden shadow rounded-lg">
                        <div class="px-4 py-5 sm:p-6">
                            <div class="flex items-center">
                                <div class="flex-shrink-0 bg-green-100 rounded-md p-3">
                                    <div class="h-6 w-6 text-green-600">
                                        <!-- Check Icon -->
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                                        </svg>
                                    </div>
                                </div>
                                <div class="ml-5 w-0 flex-1">
                                    <dl>
                                        <dt class="text-sm font-medium text-gray-500 truncate">Resolved</dt>
                                        <dd class="flex items-baseline">
                                            <div class="text-2xl font-semibold text-gray-900">
                                                {reports.filter(r => r.reportStatus === "Resolved").length}
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="bg-white overflow-hidden shadow rounded-lg">
                        <div class="px-4 py-5 sm:p-6">
                            <div class="flex items-center">
                                <div class="flex-shrink-0 bg-red-100 rounded-md p-3">
                                    <div class="h-6 w-6 text-red-600">
                                        <!-- Flag Icon -->
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 21v-4m0 0V5a2 2 0 012-2h6.5l1 1H21l-3 6 3 6h-8.5l-1-1H5a2 2 0 00-2 2zm9-13.5V9" />
                                        </svg>
                                    </div>
                                </div>
                                <div class="ml-5 w-0 flex-1">
                                    <dl>
                                        <dt class="text-sm font-medium text-gray-500 truncate">Flagged</dt>
                                        <dd class="flex items-baseline">
                                            <div class="text-2xl font-semibold text-gray-900">
                                                {reports.filter(r => r.reportStatus === "Flagged").length}
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <button on:click={()=>window.location.href='/admin_dashboard'}>
                Return to Dashboard
            </button>
        </main>
    </div>
</div>