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

import java.util.List;
import org.sonar.db.DbSession;

public class ResilientProjectIndexers {

  private final List<ProjectIndexer> indexers;

  public ResilientProjectIndexers(List<ProjectIndexer> indexers) {
    this.indexers = indexers;
  }

  public void commitAndIndex(DbSession dbSession, String projectUuid, ProjectIndexer.Cause cause) {
    indexers.forEach(i -> i.createEsQueueForIndexing(dbSession, projectUuid));
    dbSession.commit();
    indexers.forEach(i -> i.indexProject(projectUuid, cause));
  }

  public void commitAndDelete(DbSession dbSession, String projectUuid) {
    indexers.forEach(i -> i.createEsQueueForDeletion(dbSession, projectUuid));
    dbSession.commit();
    indexers.forEach(i -> i.deleteProject(projectUuid));
  }
}
