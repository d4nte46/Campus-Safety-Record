<!-- Svelte component -->
<script>
    import GSULogo from "../assets/georgia-state-university-logo-gsu.svg"
    // Form data
    let incidentDate = '';
    let incidentLocation = '';
    let incidentTypes = {
        accident: false,
        injury: false,
        propertyDamage: false,
        other: false
    };
    let otherIncidentType = '';
    let description = '';
    let personsInvolved = '';
    let actionsRequired = '';
    let reportedBy = '';

    function handleSubmit() {
        // Submit logic would go here
        console.log('Incident report submitted:', {
            incidentDate,
            incidentLocation,
            incidentTypes,
            otherIncidentType,
            description,
            personsInvolved,
            actionsRequired,
            reportedBy
        });
    }
</script>

    <div class="w-full max-w-3xl p-8 mx-auto bg-gray-100 rounded-md shadow-lg">
        <!-- Header -->
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-black">Incident Report</h1>
            <div class="text-right">
                <img src={GSULogo} alt="Georgia State University Logo" class="w-20 mb-2 text-black"/>
            </div>
        </div>

        <!-- Main Form -->
        <div class="p-6 border border-amber-200 rounded-md bg-white">
            <form on:submit|preventDefault={handleSubmit} class="space-y-6">
                <!-- Date and Location -->
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="date" class="block text-sm font-medium text-gray-700">Date of Incident</label>
                        <input
                                id="date"
                                type="date"
                                bind:value={incidentDate}
                                class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                        />
                    </div>
                    <div>
                        <label for="location" class="block text-sm font-medium text-gray-700">Location of Incident</label>
                        <input
                                id="location"
                                type="text"
                                bind:value={incidentLocation}
                                class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                        />
                    </div>
                </div>

                <!-- Incident Type -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Incident Type (Check all that apply)</label>
                    <div class="flex flex-wrap gap-4">
                        <label class="inline-flex items-center">
                            <input type="checkbox" bind:checked={incidentTypes.accident} class="form-checkbox h-5 w-5 text-gray-600" />
                            <span class="ml-2 text-gray-700">Accident</span>
                        </label>
                        <label class="inline-flex items-center">
                            <input type="checkbox" bind:checked={incidentTypes.injury} class="form-checkbox h-5 w-5 text-gray-600" />
                            <span class="ml-2 text-gray-700">Injury</span>
                        </label>
                        <label class="inline-flex items-center">
                            <input type="checkbox" bind:checked={incidentTypes.propertyDamage} class="form-checkbox h-5 w-5 text-gray-600" />
                            <span class="ml-2 text-gray-700">Property Damage</span>
                        </label>
                        <label class="inline-flex items-center">
                            <input type="checkbox" bind:checked={incidentTypes.other} class="form-checkbox h-5 w-5 text-gray-600" />
                            <span class="ml-2 text-gray-700">Other:</span>
                            <input
                                    type="text"
                                    bind:value={otherIncidentType}
                                    class="ml-2 px-2 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                            />
                        </label>
                    </div>
                </div>

                <!-- Description -->
                <div>
                    <label for="description" class="block text-sm font-medium text-gray-700">Description of Incident</label>
                    <textarea
                            id="description"
                            bind:value={description}
                            rows="4"
                            placeholder="Briefly describe what happened"
                            class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                    ></textarea>
                </div>

                <!-- Persons Involved -->
                <div>
                    <label for="persons" class="block text-sm font-medium text-gray-700">Persons Involved</label>
                    <textarea
                            id="persons"
                            bind:value={personsInvolved}
                            rows="3"
                            placeholder="Name, Role, and Contact Information"
                            class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                    ></textarea>
                </div>

                <!-- Actions Taken -->
                <div>
                    <label for="actions" class="block text-sm font-medium text-gray-700">Immediate Actions Taken</label>
                    <textarea
                            id="actions"
                            bind:value={actionsRequired}
                            rows="3"
                            placeholder="What was done immediately following the incident"
                            class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-black"
                    ></textarea>
                </div>

                <!-- Submit Button -->
                <div class="flex justify-end">
                    <button
                            type="submit"
                            class="px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-gray-900 hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
                    >
                        Submit Report
                    </button>
                </div>
            </form>
        </div>
    </div>