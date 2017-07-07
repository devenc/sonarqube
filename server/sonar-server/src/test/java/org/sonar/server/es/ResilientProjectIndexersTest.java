/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.es;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.sonar.db.DbSession;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ResilientProjectIndexersTest {

  @Test
  public void commitAndIndex_must_call_createEsQueueForIndexing_commit_and_indexProject() {
    List<ProjectIndexer> indexers = new ArrayList<>();
    IntStream.rangeClosed(1,5).forEach(i -> indexers.add(mock(ProjectIndexer.class)));

    ResilientProjectIndexers underTest = new ResilientProjectIndexers(indexers);

    for (ProjectIndexer.Cause cause : ProjectIndexer.Cause.values()) {
      DbSession mockedDbSession = mock(DbSession.class);
      String projectUuid = RandomStringUtils.random(10);

      underTest.commitAndIndex(mockedDbSession, projectUuid, cause);

      indexers.forEach(i -> verify(i).createEsQueueForIndexing(eq(mockedDbSession), eq(projectUuid)));
      verify(mockedDbSession).commit();
      indexers.forEach(i -> verify(i).indexProject(eq(projectUuid), eq(cause)));
    }
  }

  @Test
  public void commitAndDelete_must_call_createEsQueueForDeletion_commit_and_deleteProject() {
    List<ProjectIndexer> indexers = new ArrayList<>();
    IntStream.rangeClosed(1,5).forEach(i -> indexers.add(mock(ProjectIndexer.class)));

    ResilientProjectIndexers underTest = new ResilientProjectIndexers(indexers);

    for (ProjectIndexer.Cause cause : ProjectIndexer.Cause.values()) {
      DbSession mockedDbSession = mock(DbSession.class);
      String projectUuid = RandomStringUtils.random(10);

      underTest.commitAndDelete(mockedDbSession, projectUuid);

      indexers.forEach(i -> verify(i).createEsQueueForDeletion(eq(mockedDbSession), eq(projectUuid)));
      verify(mockedDbSession).commit();
      indexers.forEach(i -> verify(i).deleteProject(eq(projectUuid)));
    }
  }
}
