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

package org.sonar.db.issue;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import java.util.List;
import javax.annotation.CheckForNull;
import org.sonar.api.resources.Scopes;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.rules.RuleType;

public class IssueForIndexingDto {

  static final Splitter TAGS_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
  static final Splitter MODULE_PATH_SPLITTER = Splitter.on('.').trimResults().omitEmptyStrings();

  private String key;
  private String projectUuid;
  private long updatedAt;
  private String assignee;
  private Double gap;
  private String attributes;
  private Integer line;
  private String message;
  private String resolution;
  private String severity;
  private boolean manualSeverity;
  private String checksum;
  private String status;
  private Long effort;
  private String authorLogin;
  private long issueCloseDate;
  private long issueCreationDate;
  private long issueUpdateDate;
  private String pluginName;
  private String pluginRuleKey;
  private String language;
  private String componentUuid;
  private String moduleUuidPath;
  private String path;
  private String scope;
  private String organizationUuid;
  private String tags;
  private int issueType;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getProjectUuid() {
    return projectUuid;
  }

  public void setProjectUuid(String projectUuid) {
    this.projectUuid = projectUuid;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public Double getGap() {
    return gap;
  }

  public void setGap(Double gap) {
    this.gap = gap;
  }

  public String getAttributes() {
    return attributes;
  }

  public void setAttributes(String attributes) {
    this.attributes = attributes;
  }

  public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public boolean getManualSeverity() {
    return manualSeverity;
  }

  public void setManualSeverity(boolean manualSeverity) {
    this.manualSeverity = manualSeverity;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getEffort() {
    return effort;
  }

  public void setEffort(Long effort) {
    this.effort = effort;
  }

  public String getAuthorLogin() {
    return authorLogin;
  }

  public void setAuthorLogin(String authorLogin) {
    this.authorLogin = authorLogin;
  }

  public long getIssueCloseDate() {
    return issueCloseDate;
  }

  public void setIssueCloseDate(long issueCloseDate) {
    this.issueCloseDate = issueCloseDate;
  }

  public long getIssueCreationDate() {
    return issueCreationDate;
  }

  public void setIssueCreationDate(long issueCreationDate) {
    this.issueCreationDate = issueCreationDate;
  }

  public long getIssueUpdateDate() {
    return issueUpdateDate;
  }

  public void setIssueUpdateDate(long issueUpdateDate) {
    this.issueUpdateDate = issueUpdateDate;
  }

  public String getPluginName() {
    return pluginName;
  }

  public void setPluginName(String pluginName) {
    this.pluginName = pluginName;
  }

  public String getPluginRuleKey() {
    return pluginRuleKey;
  }

  public void setPluginRuleKey(String pluginRuleKey) {
    this.pluginRuleKey = pluginRuleKey;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getComponentUuid() {
    return componentUuid;
  }

  public void setComponentUuid(String componentUuid) {
    this.componentUuid = componentUuid;
  }

  public String getModuleUuidPath() {
    return moduleUuidPath;
  }

  public void setModuleUuidPath(String moduleUuidPath) {
    this.moduleUuidPath = moduleUuidPath;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getOrganizationUuid() {
    return organizationUuid;
  }

  public void setOrganizationUuid(String organizationUuid) {
    this.organizationUuid = organizationUuid;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public int getIssueType() {
    return issueType;
  }

  public void setIssueType(int issueType) {
    this.issueType = issueType;
  }

  public RuleKey getRuleKey() {
    return RuleKey.of(pluginName, pluginRuleKey);
  }

  public String getModuleUuid() {
    return Iterators.getLast(MODULE_PATH_SPLITTER.split(moduleUuidPath).iterator());
  }

  @CheckForNull
  public String getFilePath() {
    if (path != null && !Scopes.PROJECT.equals(scope)) {
      return path;
    }
    return null;
  }

  @CheckForNull
  public String getDirectoryPath() {
    if (path != null) {
      if (Scopes.DIRECTORY.equals(scope)) {
        return path;
      }
      int lastSlashIndex = CharMatcher.anyOf("/").lastIndexIn(path);
      if (lastSlashIndex > 0) {
        return path.substring(0, lastSlashIndex);
      }
      return "/";
    }
    return null;
  }

  public RuleType getRuleType() {
    return RuleType.valueOf(issueType);
  }

  public List<String> getTagsAsList() {
    return ImmutableList.copyOf(TAGS_SPLITTER.split(tags == null ? "" : tags));
  }
}
