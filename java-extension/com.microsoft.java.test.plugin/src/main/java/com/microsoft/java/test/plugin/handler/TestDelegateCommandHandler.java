/*******************************************************************************
* Copyright (c) 2017, 2019 Microsoft Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Microsoft Corporation - initial API and implementation
*******************************************************************************/

package com.microsoft.java.test.plugin.handler;

import com.microsoft.java.test.plugin.launchers.JUnitLaunchUtils;
import com.microsoft.java.test.plugin.util.ProjectTestUtils;
import com.microsoft.java.test.plugin.util.TestGenerationUtils;
import com.microsoft.java.test.plugin.util.TestSearchUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.ls.core.internal.IDelegateCommandHandler;

import java.util.List;

@SuppressWarnings("restriction")
public class TestDelegateCommandHandler implements IDelegateCommandHandler {

    private static final String GET_TEST_SOURCE_PATH = "vscode.java.test.get.testpath";
    private static final String SEARCH_TEST_ITEMS = "vscode.java.test.search.items";
    private static final String SEARCH_TEST_ALL_ITEMS = "vscode.java.test.search.items.all";
    private static final String SEARCH_TEST_CODE_LENS = "vscode.java.test.search.codelens";
    private static final String SEARCH_TEST_LOCATION = "vscode.java.test.search.location";
    private static final String RESOLVE_JUNIT_ARGUMENT = "vscode.java.test.junit.argument";
    private static final String GENERATE_TESTS = "vscode.java.test.generateTests";

    @Override
    public Object executeCommand(String commandId, List<Object> arguments, IProgressMonitor monitor) throws Exception {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        switch (commandId) {
            case GET_TEST_SOURCE_PATH:
                return ProjectTestUtils.listTestSourcePaths(arguments, monitor);
            case SEARCH_TEST_ITEMS:
                return TestSearchUtils.searchTestItems(arguments, monitor);
            case SEARCH_TEST_ALL_ITEMS:
                return TestSearchUtils.searchAllTestItems(arguments, monitor);
            case SEARCH_TEST_CODE_LENS:
                return TestSearchUtils.searchCodeLens(arguments, monitor);
            case SEARCH_TEST_LOCATION:
                return TestSearchUtils.searchLocation(arguments, monitor);
            case RESOLVE_JUNIT_ARGUMENT:
                return JUnitLaunchUtils.resolveLaunchArgument(arguments, monitor);
            case GENERATE_TESTS:
                return TestGenerationUtils.generateTests(arguments, monitor);
            default:
                throw new UnsupportedOperationException(
                        String.format("Java test plugin doesn't support the command '%s'.", commandId));
        }
    }
}
